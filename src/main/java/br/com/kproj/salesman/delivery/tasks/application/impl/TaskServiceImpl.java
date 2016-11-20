package br.com.kproj.salesman.delivery.tasks.application.impl;

import br.com.kproj.salesman.delivery.tasks.application.TaskFacade;
import br.com.kproj.salesman.delivery.tasks.application.validators.TaskIgnoreRules;
import br.com.kproj.salesman.delivery.tasks.domain.model.delivery.Delivery;
import br.com.kproj.salesman.delivery.tasks.domain.model.sales.SalesOrder;
import br.com.kproj.salesman.delivery.tasks.domain.model.sales.SalesValidator;
import br.com.kproj.salesman.delivery.tasks.domain.model.subscribe.ChangeStatus;
import br.com.kproj.salesman.delivery.tasks.domain.model.subscribe.Subscriber;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.Task;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.TaskRepository;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.TaskValidator;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static br.com.kproj.salesman.delivery.tasks.application.validators.TaskIgnoreRules.ruleInvalidDelivery;
import static br.com.kproj.salesman.delivery.tasks.domain.model.subscribe.Subscriber.subscriber;

@Service
public class TaskServiceImpl extends BaseModelServiceImpl<Task> implements TaskFacade {


    private TaskRepository repository;

    private TaskValidator validator;

    private SalesValidator salesValidator;

    @Autowired
    public TaskServiceImpl(TaskRepository repository, TaskValidator validator, SalesValidator salesValidator) {
        this.repository = repository;
        this.validator = validator;
        this.salesValidator = salesValidator;
    }

    public Optional<Task> register(Task task) {
        if (task.isNew()) {
            validator.checkRules(task);
        } else {
            validator.checkRules(task, TaskIgnoreRules.add(ruleInvalidDelivery()));
        }

        return subscriber().save(task);
    }

    @Override
    public Iterable<Task> findAll(Delivery delivery, Pageable pageable) {
        return repository.findAll(delivery, pageable);
    }

    @Override
    public void generateByNewSalesOrder(SalesOrder salesOrder) {
        salesValidator.checkRules(salesOrder);
        repository.generateTaskFor(salesOrder);
    }

    @Override
    public void changeStatus(ChangeStatus changeStatus) {
        //refactory: subscriber.changeTo(newStatus).of(task)
        repository.changeStatus(changeStatus);
    }

    @Override
    public BaseRepository<Task, Long> getRepository() {
        return repository;
    }
}
