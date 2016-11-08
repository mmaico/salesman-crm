package br.com.kproj.salesman.products_catalog.delivery_definition.application.impl;


import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import br.com.kproj.salesman.products_catalog.delivery_definition.application.SubTaskFacade;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.product.SaleableRepository;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static com.google.common.collect.Sets.newHashSet;

@Service("subTaskDefinitionService")
public class SubTaskServiceImpl extends BaseModelServiceImpl<Subtask> implements SubTaskFacade {


    private SubtaskRepository repository;

    private RootTaskRepository rootTaskRepository;

    private SubtaskValidator validator;

    @Autowired
    public SubTaskServiceImpl(SubtaskRepository repository, RootTaskRepository rootTaskRepository, SubtaskValidator validator) {
        this.repository = repository;
        this.rootTaskRepository = rootTaskRepository;
        this.validator = validator;
    }

    @Override
    public Collection<Subtask> findAll(RootTask rootTask) {
        return repository.findAll(rootTask);
    }

    @Override
    public void delete(Subtask task) {

        if (task.isNew()) {
            hasErrors(newHashSet("invalid.task.template.id")).throwing(ValidationException.class);
        }
        repository.delete(task);
    }

    @Override
    public Optional<Subtask> register(SubtaskToRootTask subtaskToRootTask) {
        validator.checkRules(subtaskToRootTask);
        RootTask rootTask = subtaskToRootTask.getAsRootTask();
        return rootTask.addSubTask(subtaskToRootTask.getSubtask());
    }

    @Override
    public BaseRepository<Subtask, Long> getRepository() {
        return repository;
    }


}
