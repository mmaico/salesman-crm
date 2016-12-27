package br.com.kproj.salesman.assistants.activities.application.validators;

import br.com.kproj.salesman.assistants.activities.domain.model.personal.Activity;
import br.com.kproj.salesman.assistants.activities.domain.model.personal.ActivityValidator;
import br.com.kproj.salesman.assistants.activities.domain.model.user.AssignerRepository;
import br.com.kproj.salesman.assistants.activities.domain.model.user.OwnerRepository;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.validators.CheckRule;
import br.com.kproj.salesman.infrastructure.validators.RuleKey;
import br.com.kproj.salesman.infrastructure.validators.RulesExecute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static br.com.kproj.salesman.infrastructure.helpers.RuleExpressionHelper.description;
import static br.com.kproj.salesman.infrastructure.validators.RuleKey.key;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Component
public class ActivityBusinessRules implements ActivityValidator {

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private AssignerRepository assignerRepository;

    private Map<RuleKey, CheckRule<Activity>> rules = new HashMap<>();

    {
        rules.put(key("activity.invalid.title", "title"), (activity) -> isBlank(activity.getTitle()));

        rules.put(key("activity.invalid.deadline", "deadline" ), (activity) -> activity.getDeadline() == null || activity.getDeadline().before(new Date()));

        rules.put(key("activity.invalid.user", "owner"), (activity) -> activity.getOwner() == null
                || activity.getOwner().isNew()
                || !ownerRepository.findOne(activity.getOwner().getId()).isPresent());

        rules.put(key("activity.invalid.assignment", "assigner"), (activity) ->
                activity.getAssigner() == null || activity.getAssigner().isNew() || !assignerRepository.findOne(activity.getAssigner().getId()).isPresent());
    }

    @Override
    public void checkRules(Activity activity) {
        RulesExecute.runRules(rules, activity);
    }
}
