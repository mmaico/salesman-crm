package br.com.kproj.salesman.timelines.activities.application.validators;

import br.com.kproj.salesman.infrastructure.validators.CheckRule;
import br.com.kproj.salesman.infrastructure.validators.RuleKey;
import br.com.kproj.salesman.infrastructure.validators.RulesExecute;
import br.com.kproj.salesman.timelines.activities.application.validators.descriptions.ActivityRulesDescription;
import br.com.kproj.salesman.timelines.activities.domain.model.activities.activity.Activity;
import br.com.kproj.salesman.timelines.activities.domain.model.activities.activity.ActivityValidator;
import br.com.kproj.salesman.timelines.activities.domain.model.timeline.TimelineRepository;
import br.com.kproj.salesman.timelines.activities.domain.model.user.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static br.com.kproj.salesman.timelines.activities.application.validators.descriptions.ActivityRulesDescription.*;

@Component("activityBusinessRulesTimelineActivitiesModule")
public class ActivityBusinessRules implements ActivityValidator {

    private TimelineRepository repository;

    private UserRepository userRepository;

    @Autowired
    public ActivityBusinessRules(TimelineRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    private Map<RuleKey, CheckRule<Activity>> rules = new HashMap<>();
    {
        rules.put(ruleValidDescription(), activity -> StringUtils.isBlank(activity.getDescription()));

        rules.put(ruleTag(), activity -> activity.getTag() == null);

        rules.put(ruleValidTimeline(), activity ->
            activity.getTimeline() == null || activity.getTimeline().isNew() || !repository.findOne(activity.getTimeline().getId()).isPresent()
        );

        rules.put(ruleValidUser(), activity ->
            activity.getUser() == null || activity.getUser().isNew() || !userRepository.findOne(activity.getUser().getId()).isPresent()
        );


    }

    @Override
    public void checkRules(Activity task) {
        RulesExecute.runRules(rules, task);
    }

    public void checkRules(Activity activity, ActivityRulesDescription ignoreRules) {
        RulesExecute.runRules(rules, activity, ignoreRules);
    }
}
