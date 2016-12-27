package br.com.kproj.salesman.assistants.activities.application.validators;

import br.com.kproj.salesman.assistants.activities.domain.model.personal.Activity;
import br.com.kproj.salesman.assistants.activities.domain.model.personal.ActivityRepository;
import br.com.kproj.salesman.assistants.activities.domain.model.personal.rootactivity.RootActivity;
import br.com.kproj.salesman.assistants.activities.domain.model.personal.rootactivity.RootActivityValidator;
import br.com.kproj.salesman.infrastructure.validators.CheckRule;
import br.com.kproj.salesman.infrastructure.validators.RuleKey;
import br.com.kproj.salesman.infrastructure.validators.RulesExecute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static br.com.kproj.salesman.assistants.activities.domain.model.personal.Represent.NO_REPRESENT;
import static br.com.kproj.salesman.infrastructure.validators.RuleKey.key;

@Component
public class RootActivityBusinessRules implements RootActivityValidator {

    @Autowired
    private ActivityRepository repository;


    Map<RuleKey, CheckRule<RootActivity>> rules = new HashMap<>();
    {

        rules.put(key("rootactivity.with.invalid.activity.id"), rootActivity ->
            rootActivity.isNew() && !repository.findOne(rootActivity.getId()).isPresent()
        );

        rules.put(key("rootactivity.with.already.specialized"), rootActivity -> {
            Optional<Activity> activity = repository.findOne(rootActivity.getId());
            return !NO_REPRESENT.equals(activity.get().getRepresent());
        });
    }

    @Override
    public void checkRules(RootActivity rootActivity) {
        RulesExecute.runRules(rules, rootActivity);
    }
}
