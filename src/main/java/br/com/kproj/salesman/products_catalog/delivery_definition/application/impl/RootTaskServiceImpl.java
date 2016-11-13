package br.com.kproj.salesman.products_catalog.delivery_definition.application.impl;


import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import br.com.kproj.salesman.products_catalog.delivery_definition.application.RootTaskFacade;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.product.Saleable;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.roottasks.RootTask;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.roottasks.RootTaskRepository;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.roottasks.RootTaskToSaleable;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.roottasks.RootTaskValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static com.google.common.collect.Sets.newHashSet;

@Service("rootTaskDefinitionService")
public class RootTaskServiceImpl extends BaseModelServiceImpl<RootTask> implements RootTaskFacade {


    private RootTaskRepository repository;

    private RootTaskValidator validator;

    @Autowired
    public RootTaskServiceImpl(RootTaskRepository repository, RootTaskValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }


    @Override
    public void delete(RootTask task) {

        if (task.isNew()) {
            hasErrors(newHashSet("invalid.task.template.id")).throwing(ValidationException.class);
        }
        repository.delete(task);
    }

    @Override
    public Optional<RootTask> register(RootTaskToSaleable taskToSaleable) {
        validator.checkRules(taskToSaleable);
        Saleable saleable = taskToSaleable.getAsASaleable();

        return saleable.addRootTask(taskToSaleable.getTask());
    }

    @Override
    public Page<RootTask> findAll(Pageable page) {
        return repository.findAll(page);
    }

    @Override
    public BaseRepository<RootTask, Long> getRepository() {
        return repository;
    }


}
