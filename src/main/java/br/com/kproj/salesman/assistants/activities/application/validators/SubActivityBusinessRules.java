package br.com.kproj.salesman.assistants.activities.application.validators;

import br.com.kproj.salesman.assistants.activities.domain.model.personal.Activity;
import br.com.kproj.salesman.assistants.activities.domain.model.personal.ActivityRepository;
import br.com.kproj.salesman.assistants.activities.domain.model.personal.subactivity.SubActivity;
import br.com.kproj.salesman.assistants.activities.domain.model.personal.subactivity.SubActivityValidator;
import br.com.kproj.salesman.infrastructure.validators.CheckRule;
import br.com.kproj.salesman.infrastructure.validators.RuleKey;
import br.com.kproj.salesman.infrastructure.validators.RulesExecute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static br.com.kproj.salesman.assistants.activities.domain.model.personal.Represent.NO_REPRESENT;
import static br.com.kproj.salesman.assistants.activities.domain.model.personal.Represent.ROOTACTIVITY;
import static br.com.kproj.salesman.infrastructure.validators.RuleKey.key;

@Component
public class SubActivityBusinessRules implements SubActivityValidator {

    @Autowired
    private ActivityRepository repository;


    Map<RuleKey, CheckRule<SubActivity>> rules = new HashMap<>();
    {
        rules.put(key("subactivity.with.invalid.parent"), subActivity -> {
            Activity parent = subActivity.getParent();
            Optional<Activity> activityFound = repository.findOne(parent.getId());

            return parent == null && parent.isNew() && !activityFound.isPresent() && !ROOTACTIVITY.equals(activityFound.get().getRepresent());
        });

        rules.put(key("subactivity.with.invalid.activity.id"), subActivity ->
            subActivity.isNew() && !repository.findOne(subActivity.getId()).isPresent()
        );

        rules.put(key("subactivity.with.already.specialized"), subActivity -> {

            Optional<Activity> activity = repository.findOne(subActivity.getId());

            return !NO_REPRESENT.equals(activity.get().getRepresent());
        });
    }

    @Override
    public void checkRules(SubActivity subActivity) {
        RulesExecute.runRules(rules, subActivity);
    }
}
