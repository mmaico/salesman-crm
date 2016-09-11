package br.com.kproj.salesman.delivery.application.tasks;

import br.com.kproj.salesman.delivery.domain.TaskDomainService;
import br.com.kproj.salesman.delivery.infrastructure.dtos.DeliveryResumeExecutionTaskDTO;
import br.com.kproj.salesman.delivery.infrastructure.generatebysalesorder.SalesOrderTaskItemProcessor;
import br.com.kproj.salesman.delivery.infrastructure.repository.TaskChangeHistoryRepository;
import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.enums.TaskStatus;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrderEntity;
import br.com.kproj.salesman.infrastructure.entity.task.TaskEntity;
import br.com.kproj.salesman.infrastructure.entity.task.TaskChangeHistory;
import br.com.kproj.salesman.infrastructure.events.messages.TaskChangeMessage;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.task.TaskRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceLegacyImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.eventbus.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static br.com.kproj.salesman.infrastructure.entity.builders.TaskChangeHistoryBuilder.createTaskChangeHistory;
import static br.com.kproj.salesman.infrastructure.helpers.CollectionsHelper.isEmptySafe;
import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static br.com.kproj.salesman.infrastructure.helpers.ModelHelper.isNull;
import static com.google.common.collect.Sets.newHashSet;
import static java.util.Collections.emptySet;

@Service
public class TaskApplicationImpl extends BaseModelServiceLegacyImpl<TaskEntity> implements TaskApplication {

    @Autowired
    private TaskRepository repository;

    @Autowired
    private TaskChangeHistoryRepository changeHistoryRepository;

    @Autowired
    private TaskDomainService service;

    @Autowired
    private SalesOrderTaskItemProcessor processor;

    @Autowired
    private EventBus eventBus;



    @Override
    public TaskEntity register(TaskEntity taskEntity) {
        TaskEntity taskEntitySaved;

        if (!taskEntity.isNew()) {
            taskEntitySaved =  super.save(taskEntity, service);
        } else {
            service.checkBusinessRulesFor(taskEntity);
            service.prepareToSave(taskEntity);
            taskEntitySaved = super.save(taskEntity);
        }

        if (taskEntity.hasValidParent()) {
            Optional<TaskEntity> parentLoaded = repository.getOne(taskEntity.getParent().getId());
            if (parentLoaded.isPresent()) {
                parentLoaded.get().addChild(taskEntitySaved);
            }
        }
        eventBus.post(TaskChangeMessage.create(taskEntitySaved));
        return taskEntitySaved;
    }

    @Override
    public TaskEntity registerSubtask(TaskEntity parent, TaskEntity taskEntityChild) {


        Optional<TaskEntity> taskParentLoaded = repository.getOne(parent.getId());

        if (!taskParentLoaded.isPresent()) {
            throw new ValidationException(Sets.newHashSet("subtask.with.invalid.parent"));
        }

        taskEntityChild.setParent(taskParentLoaded.get());
        taskEntityChild.setSalesOrderEntity(taskParentLoaded.get().getSalesOrderEntity());
        taskEntityChild.setRegion(taskParentLoaded.get().getRegion());


        return register(taskEntityChild);
    }

    @Override
    public void generateTaskByNewSalesOrder(SalesOrderEntity salesOrderEntity) throws Exception {

        List<TaskEntity> result = repository.findBySalesOrder(salesOrderEntity);
        if (!result.isEmpty()) {
            hasErrors(Sets.newHashSet("already.generate.task.for.this.sales"))
                    .throwing(ValidationException.class);
        }

        List<TaskEntity> taskEntities = processor.process(salesOrderEntity);

        if (taskEntities.isEmpty()) return;

        this.repository.save(taskEntities);
    }

    @Override
    public List<TaskEntity> findBySaleOrder(SalesOrderEntity salesOrderEntity) {

        hasErrors(isNull(salesOrderEntity) || salesOrderEntity.isNew() ? newHashSet("invalid.salesorder.list.tasks") : emptySet())
                .throwing(ValidationException.class);

        return repository.findBySalesOrder(salesOrderEntity);

    }

    @Override
    public Boolean isSomeonesSon(TaskEntity taskEntity) {

        if (taskEntity == null || taskEntity.isNew()) {
            return Boolean.FALSE;
        }

        return repository.isSomeonesSon(taskEntity);
    }

    @Override
    public void changeStatus(TaskEntity taskEntity, UserEntity userChange) {

        TaskEntity taskEntityLoaded = repository.findOne(taskEntity.getId());

        hasErrors(isNull(taskEntityLoaded) ? newHashSet("task.not.found") : emptySet())
                .throwing(ValidationException.class);

        taskEntityLoaded.setStatus(taskEntity.getStatus());

        TaskChangeHistory history = createTaskChangeHistory(taskEntityLoaded, taskEntity.getStatus()).build();
        this.changeHistoryRepository.save(history);
    }

    @Override
    public DeliveryResumeExecutionTaskDTO getResume() {

        DeliveryResumeExecutionTaskDTO taskDTO = DeliveryResumeExecutionTaskDTO.create()
                .add(TaskStatus.DONE, repository.countByStatus(TaskStatus.DONE))
                .add(TaskStatus.STATED, repository.countByStatus(TaskStatus.STATED))
                .add(TaskStatus.WAITING, repository.countByStatus(TaskStatus.WAITING))
                .add(TaskStatus.PROBLEM, repository.countByStatus(TaskStatus.PROBLEM));

        return taskDTO;
    }

    @Override
    public DeliveryResumeExecutionTaskDTO getResume(SalesOrderEntity salesOrderEntity) {
        DeliveryResumeExecutionTaskDTO taskDTO = DeliveryResumeExecutionTaskDTO.create()
                .add(TaskStatus.DONE, repository.countByStatus(TaskStatus.DONE, salesOrderEntity))
                .add(TaskStatus.STATED, repository.countByStatus(TaskStatus.STATED, salesOrderEntity))
                .add(TaskStatus.WAITING, repository.countByStatus(TaskStatus.WAITING, salesOrderEntity))
                .add(TaskStatus.PROBLEM, repository.countByStatus(TaskStatus.PROBLEM, salesOrderEntity));

        return taskDTO;
    }

    @Override
    public Long countBySalesOrder(SalesOrderEntity salesOrderEntity) {
        if (salesOrderEntity == null || salesOrderEntity.isNew()) return 0l;
        return this.repository.countBySalesOrder(salesOrderEntity);
    }

    @Override
    public void signedTask(UserEntity user, TaskEntity taskEntity) {

        if (taskEntity == null || taskEntity.isNew()) {
            hasErrors(Sets.newHashSet("task.signed.task.is.invalid")).throwing(ValidationException.class);
        }

        Optional<TaskEntity> taskLoaded = this.repository.getOne(taskEntity.getId());

        if (taskLoaded.isPresent()) {
            if (!taskLoaded.get().hasSigned(user)) {
                taskLoaded.get().addSignedBy(user);
            }
        }
    }

    @Override
    public void unsignedTask(UserEntity user, TaskEntity taskEntity) {
        if (taskEntity == null || taskEntity.isNew()) {
            hasErrors(Sets.newHashSet("task.signed.task.is.invalid")).throwing(ValidationException.class);
        }

        Optional<TaskEntity> taskLoaded = this.repository.getOne(taskEntity.getId());

        if (taskLoaded.isPresent()) {
            if (taskLoaded.get().hasSigned(user)) {
                if (!isEmptySafe(taskLoaded.get().getSignedBy())) {
                    taskLoaded.get().getSignedBy().remove(user);
                }
            }
        }
    }

    @Override
    public List<TaskEntity> findTaskRootBy(SalesOrderEntity salesOrderEntity) {

        if (salesOrderEntity.isNew()) {
            Lists.newArrayList();
        }

        return this.repository.findTaskRootBy(salesOrderEntity);
    }


    public BaseRepositoryLegacy<TaskEntity, Long> getRepository() {
        return repository;
    }


}
