package br.com.kproj.salesman.products_catalog.delivery_definition.application.impl;


import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import br.com.kproj.salesman.products_catalog.delivery_definition.application.ChecklistFacade;
import br.com.kproj.salesman.products_catalog.delivery_definition.application.validators.ChecklistIgnoreRules;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.checklist.Checklist;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.checklist.ChecklistRepository;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.checklist.ChecklistToTask;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.checklist.ChecklistValidator;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.Task;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

import static br.com.kproj.salesman.products_catalog.delivery_definition.application.validators.ChecklistIgnoreRules.*;
import static br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.checklist.ChecklistToTask.createChecklistToTask;

@Service("checklistDefinitionFacadeTaskTemplateModule")
public class ChecklistServiceImpl extends BaseModelServiceImpl<Checklist> implements ChecklistFacade {

    private ChecklistRepository repository;
    private ChecklistValidator validator;

    @Autowired
    public ChecklistServiceImpl(ChecklistRepository repository, ChecklistValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }


    @Override
    public Collection<Checklist> findAll(Task task) {
        return repository.findAll(task);
    }

    @Override
    public void delete(Checklist checklist) {
        ChecklistIgnoreRules ignoreRules = ChecklistIgnoreRules.add(ruleNameNotPresent(), ruleTaskNotExists());
        validator.checkRules(createChecklistToTask(checklist), ignoreRules);

        repository.delete(checklist);
    }

    @Override
    public Optional<Checklist> register(ChecklistToTask checklistToTask) {
        validator.checkRules(checklistToTask, ChecklistIgnoreRules.add(ruleChecklistNotExists()));
        Task task = checklistToTask.getTask();
        Checklist checklist = checklistToTask.getChecklist();

        return User.user().add(checklist).in(task);
    }


    @Override
    public BaseRepository<Checklist, Long> getRepository() {
        return repository;
    }


}
