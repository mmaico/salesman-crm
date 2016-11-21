package br.com.kproj.salesman.delivery.tasks.application.impl;

import br.com.kproj.salesman.delivery.tasks.application.RootTaskFacade;
import br.com.kproj.salesman.delivery.tasks.application.TaskFacade;
import br.com.kproj.salesman.delivery.tasks.domain.model.checklist.ChecklistForTask;
import br.com.kproj.salesman.delivery.tasks.domain.model.checklist.ChecklistValidator;
import br.com.kproj.salesman.delivery.tasks.domain.model.sales.SalesOrder;
import br.com.kproj.salesman.delivery.tasks.domain.model.sales.SalesValidator;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.Task;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.TaskRepository;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.TaskValidator;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.roottask.RootTask;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.roottask.RootTaskRepository;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.roottask.RootTaskValidator;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.subtask.Subtask;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.subtask.SubtaskValidator;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class RootTaskServiceImpl extends BaseModelServiceImpl<RootTask> implements RootTaskFacade {

    private RootTaskRepository repository;

    private RootTaskValidator validator;

    @Autowired
    public RootTaskServiceImpl(RootTaskRepository repository, RootTaskValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public Optional<RootTask> register(RootTask task) {
        validator.checkRules(task);
        Optional<RootTask> result = repository.save(task);

        return result;
    }

    @Override
    public Iterable<RootTask> findAll(Long deliveryId, Pageable pageable) {
        return null;
    }

    @Override
    public BaseRepository<RootTask, Long> getRepository() {
        return repository;
    }
}
