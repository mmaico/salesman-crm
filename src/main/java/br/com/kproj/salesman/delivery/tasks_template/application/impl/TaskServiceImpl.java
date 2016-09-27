package br.com.kproj.salesman.delivery.tasks_template.application.impl;


import br.com.kproj.salesman.delivery.tasks_template.application.TaskFacade;
import br.com.kproj.salesman.delivery.tasks_template.model.product.Saleable;
import br.com.kproj.salesman.delivery.tasks_template.model.product.SaleableRepository;
import br.com.kproj.salesman.delivery.tasks_template.model.region.Region;
import br.com.kproj.salesman.delivery.tasks_template.model.tasks.Task;
import br.com.kproj.salesman.delivery.tasks_template.model.tasks.TaskRepository;
import br.com.kproj.salesman.delivery.tasks_template.model.tasks.TaskToSaleable;
import br.com.kproj.salesman.delivery.tasks_template.model.tasks.TaskToSaleableValidator;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static com.google.common.collect.Sets.newHashSet;

@Service("taskTemplateService")
public class TaskServiceImpl extends BaseModelServiceImpl<Task> implements TaskFacade {


    private TaskRepository repository;

    private SaleableRepository saleableRepository;

    private TaskToSaleableValidator validator;

    @Autowired
    public TaskServiceImpl(TaskRepository repository, SaleableRepository saleableRepository, TaskToSaleableValidator validator) {
        this.repository = repository;
        this.saleableRepository = saleableRepository;
        this.validator = validator;
    }


    @Override
    public Collection<Task> findAll(Saleable saleable) {
        return repository.findAll(saleable);
    }

    @Override
    public Collection<Task> findAll(Saleable saleable, Region region) {
        return repository.findAll(saleable, region);
    }

    @Override
    public void delete(Task task) {

        if (task.isNew()) {
            hasErrors(newHashSet("invalid.task.template.id")).throwing(ValidationException.class);
        }
        repository.delete(task);
    }

    public Optional<Task> register(TaskToSaleable taskToSaleable) {
        validator.checkRules(taskToSaleable);
        Optional<Saleable> saleable = saleableRepository.findOne(taskToSaleable.getSaleableId());

        return saleable.get().addTask(taskToSaleable.getTask());
    }


    @Override
    public BaseRepository<Task, Long> getRepository() {
        return repository;
    }


}
