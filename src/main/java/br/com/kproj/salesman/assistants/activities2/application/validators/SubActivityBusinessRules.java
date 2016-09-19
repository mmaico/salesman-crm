package br.com.kproj.salesman.assistants.activities2.application.validators;

import br.com.kproj.salesman.assistants.activities2.domain.model.personal.ActivityRepository;
import br.com.kproj.salesman.assistants.activities2.domain.model.personal.ActivityValidator;
import br.com.kproj.salesman.assistants.activities2.domain.model.personal.SubActivity;
import br.com.kproj.salesman.assistants.activities2.domain.model.personal.SubActivityValidator;
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

@Component
public class SubActivityBusinessRules implements SubActivityValidator {

    @Autowired
    private ActivityRepository repository;

    @Autowired
    private ActivityValidator activityValidator;

    Map<String, CheckRule<SubActivity>> rules = new HashMap<>();
    {
        rules.put(description("subactivity.with.invalid.parent"), subActivity -> subActivity.getParent() == null
                && subActivity.getParent().isNew() && !repository.findOne(subActivity.getParent().getId()).isPresent());
    }

    @Override
    public void checkRules(SubActivity subActivity) {

        activityValidator.checkRules(subActivity);

        Set<String> violations = rules.entrySet()
                .stream()
                .filter(e -> e.getValue().check(subActivity))
                .map(Map.Entry::getKey).collect(Collectors.toSet());

        hasErrors(violations).throwing(ValidationException.class);
    }
}
