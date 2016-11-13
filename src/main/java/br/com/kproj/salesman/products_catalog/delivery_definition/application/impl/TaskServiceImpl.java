package br.com.kproj.salesman.products_catalog.delivery_definition.application.impl;


import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import br.com.kproj.salesman.products_catalog.delivery_definition.application.TaskFacade;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.product.Saleable;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.Task;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.TaskRepository;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.TaskToSaleable;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.TaskValidator;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service("taskDefinitionService")
public class TaskServiceImpl extends BaseModelServiceImpl<Task> implements TaskFacade {


    private TaskRepository repository;
    private TaskValidator validator;


    @Autowired
    public TaskServiceImpl(TaskRepository repository, TaskValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    @Override
    public Optional<Task> register(TaskToSaleable taskToSaleable) {
        validator.checkRules(taskToSaleable);
        Saleable saleable = taskToSaleable.getAsASaleable();
        Task task = taskToSaleable.getTask();

        return User.user().createA(task).to(saleable);
    }

    @Override
    public Optional<Task> update(Task task) {
        return null;
    }

    @Override
    public Collection<Task> findAll(Saleable saleable) {
        return repository.findAll(saleable);
    }


    @Override
    public BaseRepository<Task, Long> getRepository() {
        return repository;
    }


}
