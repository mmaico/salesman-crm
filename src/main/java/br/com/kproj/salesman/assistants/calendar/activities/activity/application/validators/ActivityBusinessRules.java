package br.com.kproj.salesman.assistants.calendar.activities.activity.application.validators;


import br.com.kproj.salesman.assistants.calendar.activities.activity.domain.model.activity.Activity;
import br.com.kproj.salesman.assistants.calendar.activities.activity.domain.model.activity.ActivityRepository;
import br.com.kproj.salesman.assistants.calendar.activities.activity.domain.model.activity.ActivityValidator;
import br.com.kproj.salesman.assistants.calendar.activities.activity.domain.model.calendar.Calendar;
import br.com.kproj.salesman.assistants.calendar.activities.activity.domain.model.calendar.CalendarRepository;
import br.com.kproj.salesman.infrastructure.validators.CheckRule;
import br.com.kproj.salesman.infrastructure.validators.IgnoreRules;
import br.com.kproj.salesman.infrastructure.validators.RuleKey;
import br.com.kproj.salesman.infrastructure.validators.RulesExecute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


import static br.com.kproj.salesman.assistants.calendar.activities.activity.application.utils.DateUtils.is;
import static br.com.kproj.salesman.assistants.calendar.activities.activity.application.utils.Greater.greater;
import static br.com.kproj.salesman.assistants.calendar.activities.activity.application.utils.Than.than;
import static br.com.kproj.salesman.assistants.calendar.activities.activity.application.validators.ActivityRulesDescription.*;
import static br.com.kproj.salesman.infrastructure.helpers.DateHelper.hasHourOrMinutesSet;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Component("activityBusinessRulesCalendarModule")
public class ActivityBusinessRules implements ActivityValidator {

    private ActivityRepository repository;
    private CalendarRepository calendarRepository;

    @Autowired
    public ActivityBusinessRules(ActivityRepository repository, CalendarRepository calendarRepository) {
        this.repository = repository;
        this.calendarRepository = calendarRepository;
    }

    private Map<RuleKey, CheckRule<Activity>> rules = new HashMap<>();
    {
        rules.put(activityNotExists(), (activity -> activity.isNew()
                || !repository.findOne(activity.getId()).isPresent()));

        rules.put(ruleCalendar(), (activity -> {
            Calendar calendar = activity.getCalendar();
            return calendar == null || calendar.isNew() || !calendarRepository.findOne(calendar.getId()).isPresent();
        }));

        rules.put(ruleTitle(), (activity -> isBlank(activity.getTitle())));
        rules.put(ruleStartDate(), (activity -> activity.getStart() == null));
        rules.put(ruleEndDate(), (activity -> activity.getEnd() == null));

        rules.put(ruleRangeDate(), (activity -> is(greater(activity.getStart()), than(activity.getEnd()))));

        rules.put(ruleAllDay(), (activity ->
            activity.isAllDay() && (hasHourOrMinutesSet(activity.getStart()) || hasHourOrMinutesSet(activity.getEnd()))
        ));
    }

    @Override
    public void checkRules(Activity activity) {
        RulesExecute.runRules(rules, activity);
    }

    @Override
    public void checkRules(Activity activity, IgnoreRules ignoreRules) {
        RulesExecute.runRules(rules, activity, ignoreRules);
    }
}
