package br.com.kproj.salesman.assistants.activities.application.validators;

import br.com.kproj.salesman.assistants.activities.domain.model.personal.ActivityRepository;
import br.com.kproj.salesman.assistants.activities.domain.model.checklist.Checklist;
import br.com.kproj.salesman.assistants.activities.domain.model.checklist.ChecklistValidator;
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

@Component
public class ChecklistBusinessRules implements ChecklistValidator {

    @Autowired
    private ActivityRepository repository;


    Map<String, CheckRule<Checklist>> rules = new HashMap<>();
    {
        rules.put(description("checklist.with.invalid.name"), checklist -> isBlank(checklist.getName()));
    }

    @Override
    public void checkRules(Checklist checklist) {

        Set<String> violations = rules.entrySet()
                .stream()
                .filter(e -> e.getValue().check(checklist))
                .map(Map.Entry::getKey).collect(Collectors.toSet());

        hasErrors(violations).throwing(ValidationException.class);
    }
}
