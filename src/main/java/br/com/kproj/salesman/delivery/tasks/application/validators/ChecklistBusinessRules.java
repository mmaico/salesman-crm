package br.com.kproj.salesman.delivery.tasks.application.validators;

import br.com.kproj.salesman.delivery.tasks.domain.model.checklist.Checklist;
import br.com.kproj.salesman.delivery.tasks.domain.model.checklist.ChecklistRepository;
import br.com.kproj.salesman.delivery.tasks.domain.model.checklist.ChecklistValidator;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.TaskRepository;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.validators.CheckRule;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
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

    private Map<String, CheckRule<Checklist>> rules = new HashMap<>();
    {

        rules.put("checklist.with.invalid.task", checklist ->
                checklist.getTask().isNew() || !repository.findOne(checklist.getTask().getId()).isPresent());

        rules.put("checklist.with.invalid.name", checklist -> checklist.isNew()
                                ? isBlank(checklist.getName())
                                : checklist.getFields().contains("name") && isBlank(checklist.getName()));

        rules.put("checklist.with.invalid.status", checklist -> !checklist.isNew()
                && (checklist.getFields().contains("done") && checklist.getDone() != null));

        rules.put("checklist.with.invalid.id", checklist -> checklist.isNew()
                || !checklistRepository.findOne(checklist.getId()).isPresent());
    }

    @Override
    public void checkRules(Checklist checklist) {
        checkRules(checklist, ChecklistIgnoreRules.add(StringUtils.EMPTY));
    }

    @Override
    public void checkRules(Checklist checklist, ChecklistIgnoreRules ignoreRules) {

        Set<String> violations = rules.entrySet()
                .stream()
                .filter(rule -> {
                    try {
                        if (ignoreRules.contains(rule.getKey())) return Boolean.FALSE;
                        return rule.getValue().check(checklist);
                    } catch(Exception e) {
                        return Boolean.TRUE;
                    }
                }).map(Map.Entry::getKey).collect(Collectors.toSet());

        hasErrors(violations).throwing(ValidationException.class);
    }
}
