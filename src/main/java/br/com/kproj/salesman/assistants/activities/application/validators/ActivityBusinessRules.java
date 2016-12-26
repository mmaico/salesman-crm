package br.com.kproj.salesman.assistants.activities.application.validators;

import br.com.kproj.salesman.assistants.activities.domain.model.personal.Activity;
import br.com.kproj.salesman.assistants.activities.domain.model.personal.ActivityValidator;
import br.com.kproj.salesman.assistants.activities.domain.model.user.AssignerRepository;
import br.com.kproj.salesman.assistants.activities.domain.model.user.OwnerRepository;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.validators.CheckRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static br.com.kproj.salesman.infrastructure.helpers.RuleExpressionHelper.description;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Component
public class ActivityBusinessRules implements ActivityValidator {

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private AssignerRepository assignerRepository;

    private Map<String, CheckRule<Activity>> rules = new HashMap<>();

    {
        rules.put(description("activity.invalid.title"), (activity) -> isBlank(activity.getTitle()));

        rules.put(description("activity.invalid.deadline"), (activity) -> activity.getDeadline() == null || activity.getDeadline().before(new Date()));

        rules.put(description("activity.invalid.user"), (activity) -> activity.getOwner() == null
                || activity.getOwner().isNew()
                || !ownerRepository.findOne(activity.getOwner().getId()).isPresent());

        rules.put(description("activity.invalid.assignment"), (activity) -> activity.getAssigner() != null
                && !assignerRepository.findOne(activity.getAssigner().getId()).isPresent());
    }

    @Override
    public void checkRules(Activity activity) {

        Set<String> violations = rules.entrySet()
                .stream()
                .filter(e -> e.getValue().check(activity))
                .map(Map.Entry::getKey).collect(Collectors.toSet());

        hasErrors(violations).throwing(ValidationException.class);
    }
}
