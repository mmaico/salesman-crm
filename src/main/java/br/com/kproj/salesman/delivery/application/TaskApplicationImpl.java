package br.com.kproj.salesman.delivery.application;

import br.com.kproj.salesman.delivery.domain.TaskDomainService;
import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.enums.TaskStatus;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrder;
import br.com.kproj.salesman.infrastructure.entity.task.Task;
import br.com.kproj.salesman.infrastructure.events.messages.TaskChangeStatusMessage;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.repository.task.TaskRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import com.google.common.eventbus.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static br.com.kproj.salesman.infrastructure.helpers.ModelHelper.isNull;
import static com.google.common.collect.Sets.newHashSet;
import static java.util.Collections.emptySet;

@Service
public class TaskApplicationImpl extends BaseModelServiceImpl<Task> implements TaskApplication {

    @Autowired
    private TaskRepository repository;

    @Autowired
    private TaskDomainService service;

    @Autowired
    private EventBus eventBus;



    @Override
    public Task register(Task task) {

        if (!task.isNew()) {
            return super.save(task, service);
        } else {
            service.checkBusinessRulesFor(task);
            service.prepareToSave(task);

            return super.save(task);
        }

    }

    @Override
    public List<Task> findBySaleOrder(SalesOrder salesOrder) {

        hasErrors(isNull(salesOrder) || salesOrder.isNew() ? newHashSet("invalid.salesorder.list.tasks") : emptySet())
                .throwing(ValidationException.class);

        return repository.findBySalesOrder(salesOrder);

    }

    @Override
    public Boolean isSomeonesSon(Task task) {

        if (task == null || task.isNew()) {
            return Boolean.FALSE;
        }

        return repository.isSomeonesSon(task);
    }

    @Override
    public void changeStatus(Task task, User userChange) {

        Task taskLoaded = repository.findOne(task.getId());

        hasErrors(isNull(taskLoaded) ? newHashSet("task.not.found") : emptySet())
                .throwing(ValidationException.class);

        TaskStatus oldStatus = taskLoaded.getStatus();
        taskLoaded.setStatus(task.getStatus());

        eventBus.post(TaskChangeStatusMessage.create(taskLoaded, userChange, oldStatus));
    }

    @Override
    public BaseRepository<Task, Long> getRepository() {
        return repository;
    }


}
