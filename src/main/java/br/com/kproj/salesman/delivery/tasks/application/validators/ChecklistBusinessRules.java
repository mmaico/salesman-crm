package br.com.kproj.salesman.delivery.tasks.application.validators;

import br.com.kproj.salesman.delivery.tasks.domain.model.checklist.ChecklistForTask;
import br.com.kproj.salesman.delivery.tasks.domain.model.checklist.ChecklistValidator;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.TaskRepository;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.validators.CheckRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static br.com.kproj.salesman.infrastructure.helpers.RuleExpressionHelper.description;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Component("ChecklistBusinessRulesDeliveryModule")
public class ChecklistBusinessRules implements ChecklistValidator {

    @Autowired
    private TaskRepository repository;


    Map<String, CheckRule<ChecklistForTask>> rules = new HashMap<>();
    {
        rules.put(description("checklist.with.invalid.task"), checklist ->
                checklist.getTaskId() == null || !repository.findOne(checklist.getTaskId()).isPresent());

        rules.put(description("checklist.with.invalid.name"), checklist ->
                checklist.getChecklist() == null || isBlank(checklist.getChecklist().getName()));

    }

    @Override
    public void checkRules(ChecklistForTask checklistForTask) {

        Set<String> violations = rules.entrySet()
                .stream()
                .filter(e -> e.getValue().check(checklistForTask))
                .map(Map.Entry::getKey).collect(Collectors.toSet());

        hasErrors(violations).throwing(ValidationException.class);
    }
}
