package br.com.kproj.salesman.assistants.calendar.activities.specialization.application.validations;

import br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.activity.Activity;
import br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.activity.ActivityRepository;
import br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.activity.ActivityValidator;
import br.com.kproj.salesman.infrastructure.validators.CheckRule;
import br.com.kproj.salesman.infrastructure.validators.RuleKey;
import br.com.kproj.salesman.infrastructure.validators.RulesExecute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static br.com.kproj.salesman.infrastructure.validators.RuleKey.key;


@Component
public class ActivityBusinessRules implements ActivityValidator {


    private ActivityRepository repository;

    @Autowired
    public ActivityBusinessRules(ActivityRepository repository) {
        this.repository = repository;
    }

    private Map<RuleKey, CheckRule<Activity>> persistRules = new HashMap<>();
    {
        persistRules.put(key("calendar.activity.not.found"), activity ->
            activity.isNew() || !repository.findOne(activity.getId()).isPresent()
        );

        persistRules.put(key("calendar.activity.already.exist.specialization"), activity -> repository.hasSpecialization(activity));
    }

    @Override
    public void checkRules(Activity activity) {
        RulesExecute.runRules(persistRules, activity);
    }
}
