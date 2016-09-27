package br.com.kproj.salesman.assistants.calendar.application.validators;

import br.com.kproj.salesman.assistants.calendar.domain.model.activity.ActivityInCalendar;
import br.com.kproj.salesman.assistants.calendar.domain.model.activity.ActivityInCalendarValidator;
import br.com.kproj.salesman.assistants.calendar.domain.model.activity.ActivityRepository;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.validators.CheckRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static br.com.kproj.salesman.infrastructure.model.ConditionalOperator.not;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Component
public class ActivityInCalendarBusinessRules implements ActivityInCalendarValidator {

    private ActivityRepository repository;
    private PeriodBusinessRules periodRules;

    @Autowired
    public ActivityInCalendarBusinessRules(ActivityRepository repository, PeriodBusinessRules periodRules) {
        this.repository = repository;
        this.periodRules = periodRules;
    }

    private Map<String, CheckRule<ActivityInCalendar>> rules = new HashMap<>();
    {
        rules.put("calendar.activity.not.exist", (activityInCalendar ->
               not(activityInCalendar.getActivity().isNew())
            && not(repository.findOne(activityInCalendar.getActivity().getId()).isPresent())
        ));

        rules.put("calendar.activity.not.have.period", (activityInCalendar -> activityInCalendar.getActivity().getPeriod() == null));
        rules.put("calendar.activity.not.have.title", (activityInCalendar -> isBlank(activityInCalendar.getActivity().getTitle())));
        rules.put("calendar.activity.have.invalid.period", (activityInCalendar -> periodRules.checkRules(activityInCalendar.getActivity().getPeriod())));

    }

    @Override
    public void checkRules(ActivityInCalendar activityInCalendar) {

        Set<String> violations = rules.entrySet()
                .stream()
                .filter(e -> e.getValue().check(activityInCalendar))
                .map(Map.Entry::getKey).collect(Collectors.toSet());

        hasErrors(violations).throwing(ValidationException.class);
    }
}