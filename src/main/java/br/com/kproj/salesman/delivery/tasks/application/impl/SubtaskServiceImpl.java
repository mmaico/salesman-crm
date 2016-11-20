package br.com.kproj.salesman.delivery.tasks.application.impl;

import br.com.kproj.salesman.delivery.tasks.application.SubtaskFacade;
import br.com.kproj.salesman.delivery.tasks.domain.model.subscribe.Subscriber;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.roottask.RootTask;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.subtask.Subtask;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.subtask.SubtaskRepository;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.subtask.SubtaskToRootTask;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.subtask.SubtaskValidator;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubtaskServiceImpl extends BaseModelServiceImpl<Subtask> implements SubtaskFacade {

    private SubtaskRepository repository;

    private SubtaskValidator subtaskValidator;

    @Autowired
    public SubtaskServiceImpl(SubtaskRepository repository, SubtaskValidator subtaskValidator) {
        this.repository = repository;
        this.subtaskValidator = subtaskValidator;
    }

    @Override
    public Optional<Subtask> register(SubtaskToRootTask subtaskToRootTask) {
        Subtask subtask = subtaskToRootTask.getSubtask();
        RootTask rootTask = subtaskToRootTask.getAsRootTask();
        subtask.setParent(rootTask);

        subtaskValidator.checkRules(subtask);

        return Subscriber.subscriber().save(subtask);
    }

    @Override
    public Iterable<Subtask> findAll(RootTask rootTask, Pageable pageable) {
        return repository.findAll(rootTask, pageable);
    }


    @Override
    public BaseRepository<Subtask, Long> getRepository() {
        return repository;
    }
}
