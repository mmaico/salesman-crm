package br.com.kproj.salesman.assistants.calendar.application.validators;

import br.com.kproj.salesman.assistants.calendar.domain.model.activity.Activity;
import br.com.kproj.salesman.assistants.calendar.domain.model.activity.ActivityInCalendar;
import br.com.kproj.salesman.assistants.calendar.domain.model.activity.ActivityInCalendarValidator;
import br.com.kproj.salesman.assistants.calendar.domain.model.activity.ActivityRepository;
import br.com.kproj.salesman.infrastructure.validators.CheckRule;
import br.com.kproj.salesman.infrastructure.validators.RuleKey;
import br.com.kproj.salesman.infrastructure.validators.RulesExecute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static br.com.kproj.salesman.assistants.calendar.application.utils.DateUtils.is;
import static br.com.kproj.salesman.assistants.calendar.application.utils.Greater.greater;
import static br.com.kproj.salesman.assistants.calendar.application.utils.Than.than;
import static br.com.kproj.salesman.infrastructure.helpers.DateHelper.hasHourOrMinutesSet;
import static br.com.kproj.salesman.infrastructure.model.ConditionalOperator.not;
import static br.com.kproj.salesman.infrastructure.validators.RuleKey.key;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Component
public class ActivityInCalendarBusinessRules implements ActivityInCalendarValidator {

    private ActivityRepository repository;

    @Autowired
    public ActivityInCalendarBusinessRules(ActivityRepository repository) {
        this.repository = repository;
    }

    private Map<RuleKey, CheckRule<ActivityInCalendar>> rules = new HashMap<>();
    {
        rules.put(key("calendar.activity.not.exist"), (activityInCalendar ->
               not(activityInCalendar.getActivity().isNew())
            && not(repository.findOne(activityInCalendar.getActivity().getId()).isPresent())
        ));

        rules.put(key("calendar.activity.not.have.title"), (activityInCalendar -> isBlank(activityInCalendar.getActivity().getTitle())));

        rules.put(key("calendar.activity.without.dates"), (activityInCalendar -> {
            Activity activity = activityInCalendar.getActivity();
            return activity.getStart() == null || activity.getEnd() == null;
        }));

        rules.put(key("calendar.activity.startdate.cannotbe.greaterthan.enddate"), (activityInCalendar -> {
            Activity activity = activityInCalendar.getActivity();
            return not(is(greater(activity.getStart()), than(activity.getEnd())));
        }));

        rules.put(key("period.if.allday.cannothave.hours"), (activityInCalendar -> {
            Activity activity = activityInCalendar.getActivity();
            return activity.isAllDay() && (hasHourOrMinutesSet(activity.getStart()) || hasHourOrMinutesSet(activity.getEnd()));
        }));
    }

    @Override
    public void checkRules(ActivityInCalendar activityInCalendar) {
        RulesExecute.runRules(rules, activityInCalendar);
    }
}
