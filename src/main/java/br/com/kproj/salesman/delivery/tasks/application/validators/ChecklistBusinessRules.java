package br.com.kproj.salesman.delivery.tasks.application.validators;

import br.com.kproj.salesman.delivery.tasks.domain.model.checklist.Checklist;
import br.com.kproj.salesman.delivery.tasks.domain.model.checklist.ChecklistRepository;
import br.com.kproj.salesman.delivery.tasks.domain.model.checklist.ChecklistValidator;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.Task;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.TaskRepository;
import br.com.kproj.salesman.infrastructure.validators.CheckRule;
import br.com.kproj.salesman.infrastructure.validators.IgnoreRules;
import br.com.kproj.salesman.infrastructure.validators.RuleKey;
import br.com.kproj.salesman.infrastructure.validators.RulesExecute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static br.com.kproj.salesman.delivery.tasks.application.validators.descriptions.ChecklistRulesDescription.*;
import static br.com.kproj.salesman.infrastructure.validators.DefaultIgnoreRules.getIgnoreRules;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Component("checklistBusinessRulesDeliveryModule")
public class ChecklistBusinessRules implements ChecklistValidator {

    private TaskRepository repository;

    private ChecklistRepository checklistRepository;

    @Autowired
    public ChecklistBusinessRules(TaskRepository repository, ChecklistRepository checklistRepository) {
        this.repository = repository;
        this.checklistRepository = checklistRepository;
    }

    private Map<RuleKey, CheckRule<Checklist>> rules = new HashMap<>();
    {

        rules.put(ruleInvalidTask(), checklist -> {
            Task task = checklist.getTask();
            return task.isNew() || !repository.findOne(checklist.getTask().getId()).isPresent();
        });

        rules.put(ruleInvalidName(), checklist -> isBlank(checklist.getName()));

        rules.put(ruleInvalidStatus(), checklist -> checklist.getDone() == null);

        rules.put(ruleInvalidId(), checklist ->
            checklist.isNew() || !checklistRepository.findOne(checklist.getId()).isPresent()
        );
    }

    @Override
    public void checkRules(Checklist checklist) {
        checkRules(checklist, getIgnoreRules());
    }

    @Override
    public void checkRules(Checklist checklist, IgnoreRules ignoreRules) {
        RulesExecute.runRules(rules, checklist, ignoreRules);
    }
}
