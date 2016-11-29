package br.com.kproj.salesman.delivery.tasks.application.impl;

import br.com.kproj.salesman.delivery.tasks.application.ChecklistFacade;
import br.com.kproj.salesman.delivery.tasks.domain.model.checklist.Checklist;
import br.com.kproj.salesman.delivery.tasks.domain.model.checklist.ChecklistForTask;
import br.com.kproj.salesman.delivery.tasks.domain.model.checklist.ChecklistRepository;
import br.com.kproj.salesman.delivery.tasks.domain.model.checklist.ChecklistValidator;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.Task;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static br.com.kproj.salesman.delivery.tasks.application.validators.descriptions.ChecklistRulesDescription.*;
import static br.com.kproj.salesman.delivery.tasks.domain.model.subscribe.Subscriber.subscriber;

@Service("checklistFacadeDeliveryModule")
public class ChecklistServiceImpl extends BaseModelServiceImpl<Checklist> implements ChecklistFacade {


    private ChecklistRepository repository;
    private ChecklistValidator validator;

    @Autowired
    public ChecklistServiceImpl(ChecklistRepository repository, ChecklistValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    @Override
    public Iterable<Checklist> findAll(Task task, Pageable pageable) {
        return repository.findAll(task, pageable);
    }

    public Optional<Checklist> save(ChecklistForTask checklistForTask) {
        Checklist checklist = checklistForTask.getChecklist();
        Task task = checklistForTask.getTask();
        checklist.setTask(task);

        validator.checkRules(checklist, ignoreRules(ruleInvalidId(), ruleInvalidStatus()));

        return subscriber().save(checklist).to(task);
    }

    @Override
    public Checklist update(Checklist checklist) {
        validator.checkRules(checklist, ignoreRules(ruleInvalidTask()));

        return subscriber().update(checklist);
    }

    @Override
    public BaseRepository<Checklist, Long> getRepository() {
        return repository;
    }
}
