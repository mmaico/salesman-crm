package br.com.kproj.salesman.delivery.tasks.infrastructure.persistence;

import br.com.kproj.salesman.delivery.tasks.domain.model.sales.CouldNotGenerateTasksException;
import br.com.kproj.salesman.delivery.tasks.domain.model.sales.SalesOrder;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.Subtask;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.Task;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.TaskRepository;
import br.com.kproj.salesman.delivery.tasks.domain.model.user.ChangeStatus;
import br.com.kproj.salesman.delivery.tasks.domain.model.user.Subscribe;
import br.com.kproj.salesman.delivery.tasks.infrastructure.persistence.generatebysalesorder.SalesOrderTaskItemProcessor;
import br.com.kproj.salesman.delivery.tasks.infrastructure.persistence.springdata.TaskRepositorySpringData;
import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.enums.TaskStatusEntity;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrderEntity;
import br.com.kproj.salesman.infrastructure.entity.task.TaskEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.helpers.CollectionsHelper.isEmptySafe;
import static br.com.kproj.salesman.infrastructure.helpers.ReflectionsHelper.copyProperties;
import static com.trex.clone.BusinessModelClone.from;


@Repository
public class TaskRepositoryHibernate extends BaseRespositoryImpl<Task, TaskEntity> implements TaskRepository {

    @Autowired
    private TaskRepositorySpringData repository;

    @Autowired
    private SalesOrderTaskItemProcessor processor;


    @Override
    public Optional<Task> save(Task task) {

        TaskEntity taskToSave = from(task).convertTo(TaskEntity.class);

        if (task.isNew()) {
            taskToSave.setStatus(TaskStatusEntity.WAITING);
            TaskEntity tasksaved = repository.save(taskToSave);
            return Optional.of(getConverter().convert(tasksaved));
        } else {
            TaskEntity tasksaved = repository.save(taskToSave);
            return Optional.of(getConverter().convert(tasksaved));
        }
    }

    public Optional<Subtask> save(Subtask subtask) {

        Task parent = subtask.getParent();
        TaskEntity taskEntityParent = repository.findOne(parent.getId());

        TaskEntity taskToSave = from(subtask).convertTo(TaskEntity.class);
        taskToSave.setSalesOrderEntity(taskEntityParent.getSalesOrderEntity());

        TaskEntity tasksaved = repository.save(taskToSave);
        taskEntityParent.addChild(tasksaved);

        Task taskConverted = getConverter().convert(tasksaved);
        Subtask subtaskSaved = new Subtask();

        copyProperties(subtaskSaved, taskConverted);
        subtaskSaved.setParent(parent);

        return Optional.of(subtaskSaved);
    }

    @Override
    public Collection<Task> findAll(SalesOrder salesOrder) {
        SalesOrderEntity orderEntity = new SalesOrderEntity(salesOrder.getId());

        List<TaskEntity> entities = repository.findBySalesOrder(orderEntity);

        List<Task> tasks = entities.stream().map(entity -> getConverter().convert(entity))
                .collect(Collectors.toList());

        return tasks;
    }

    @Override
    public void generateTaskFor(SalesOrder salesOrder) {
        try {
            List<TaskEntity> tasks = processor.process(salesOrder);
            repository.save(tasks);
        } catch (Exception e) {
            throw new CouldNotGenerateTasksException(e);
        }
    }

    @Override
    public void register(Subscribe subscribe) {
        TaskEntity taskEntity = this.repository.findOne(subscribe.getTaskId());
        UserEntity userEntity = new UserEntity(subscribe.getUserId());

        if (!taskEntity.hasSigned(userEntity)) {
            taskEntity.addSignedBy(userEntity);
        }
    }

    @Override
    public void unregister(Subscribe subscribe) {
        TaskEntity taskEntity = this.repository.findOne(subscribe.getTaskId());

        if (!isEmptySafe(taskEntity.getSignedBy())) {
            taskEntity.getSignedBy().remove(new UserEntity(subscribe.getUserId()));
        }
    }

    @Override
    public void changeStatus(ChangeStatus changeStatus) {

        TaskEntity taskEntity = this.repository.findOne(changeStatus.getTaskId());

        TaskStatusEntity newStatus = TaskStatusEntity.get(changeStatus.getNewStatus().name());
        taskEntity.setStatus(newStatus);
    }


    @Override
    public BaseRepositoryLegacy<TaskEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<TaskEntity, Task> getConverter() {
        return null;
    }
}
