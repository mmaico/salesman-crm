package br.com.kproj.salesman.products_catalog.delivery_definition.application.validators;

import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.validators.CheckRule;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.checklist.ChecklistRepository;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.checklist.ChecklistToTask;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.checklist.ChecklistValidator;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.TaskRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static br.com.kproj.salesman.products_catalog.delivery_definition.application.validators.ChecklistIgnoreRules.*;
import static com.google.common.collect.Lists.newArrayList;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Component("checklistDefinitionsBusinessRules")
public class ChecklistBusinessRules implements ChecklistValidator {

    private TaskRepository taskRepository;
    private ChecklistRepository repository;

    @Autowired
    public ChecklistBusinessRules(TaskRepository taskRepository, ChecklistRepository repository) {
        this.taskRepository = taskRepository;
        this.repository = repository;
    }

    Map<String, CheckRule<ChecklistToTask>> rules = new HashMap<>();
    {
        rules.put(ruleNameNotPresent(), checklistToTask -> isBlank(checklistToTask.getChecklist().getName()));

        rules.put(ruleTaskNotExists(), checklistToTask -> !taskRepository.findOne(checklistToTask.getTaskId()).isPresent());

        rules.put(ruleChecklistNotExists(), checklistToTask -> checklistToTask.getChecklist().isNew()
                || !repository.findOne(checklistToTask.getChecklist().getId()).isPresent());
    }

    @Override
    public void checkRules(ChecklistToTask taskToSaleable, ChecklistIgnoreRules ignoreRules) {

        Set<String> violations = rules.entrySet()
                .stream()
                .filter(rule -> {
                    if (newArrayList(ignoreRules.getIgnoreRules()).contains(rule.getKey())) {
                        return Boolean.FALSE;
                    }
                    try {
                        return rule.getValue().check(taskToSaleable);
                    } catch (Exception e) {
                        return Boolean.TRUE;
                    }
                }).map(Map.Entry::getKey).collect(Collectors.toSet());

        hasErrors(violations).throwing(ValidationException.class);
    }

    @Override
    public void checkRules(ChecklistToTask checklistToTask) {
        checkRules(checklistToTask, ChecklistIgnoreRules.add(StringUtils.EMPTY));
    }
}
