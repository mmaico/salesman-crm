package br.com.kproj.salesman.timelines.activities.application.validators;

import br.com.kproj.salesman.infrastructure.validators.CheckRule;
import br.com.kproj.salesman.infrastructure.validators.RuleKey;
import br.com.kproj.salesman.infrastructure.validators.RulesExecute;
import br.com.kproj.salesman.timelines.activities.application.validators.descriptions.ActivityRulesDescription;
import br.com.kproj.salesman.timelines.activities.domain.model.activities.activity.Activity;
import br.com.kproj.salesman.timelines.activities.domain.model.activities.activity.ActivityRepository;
import br.com.kproj.salesman.timelines.activities.domain.model.activities.activity.ActivityValidator;
import br.com.kproj.salesman.timelines.activities.domain.model.timeline.TimelineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static br.com.kproj.salesman.delivery.tasks.application.validators.descriptions.TaskRulesDescription.ruleInvalidDelivery;

@Component("activityBusinessRulesTimelineActivitiesModule")
public class ActivityBusinessRules implements ActivityValidator {

    private TimelineRepository repository;

    private ActivityRepository activityRepository;

    @Autowired
    public ActivityBusinessRules(TimelineRepository repository, ActivityRepository activityRepository) {
        this.repository = repository;
        this.activityRepository = activityRepository;
    }

    private Map<RuleKey, CheckRule<Activity>> rules = new HashMap<>();
    {
        rules.put(ruleInvalidDelivery(), activity -> {

            return Boolean.TRUE;
        });

    }

    @Override
    public void checkRules(Activity task) {
        RulesExecute.runRules(rules, task);
    }

    public void checkRules(Activity activity, ActivityRulesDescription ignoreRules) {
        RulesExecute.runRules(rules, activity, ignoreRules);
    }
}
