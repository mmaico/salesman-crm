package br.com.kproj.salesman.assistants.calendar.domain;

import br.com.kproj.salesman.infrastructure.repository.CalendarActivityRepository;
import br.com.kproj.salesman.infrastructure.entity.calendar.CalendarActivity;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.validators.CheckRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static br.com.kproj.salesman.infrastructure.helpers.RuleExpressionHelper.description;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Service
public class CalendarActivityDomainServiceImpl implements CalendarActivityDomainService {

    @Autowired
    private CalendarActivityRepository repository;

    @Autowired
    private PeriodDomainService periodDomainService;

    Map<String, CheckRule<CalendarActivity>> persistRules = new HashMap<>();
    {
        persistRules.put(description("calendar.activity.not.exist"), (activity -> !activity.isNew() && !repository.exists(activity.getId())));
        persistRules.put(description("calendar.activity.not.have.period"), (activity -> activity.getPeriod() == null));
        persistRules.put(description("calendar.activity.not.have.title"), (activity -> isBlank(activity.getTitle())));
        persistRules.put(description("calendar.activity.have.invalid.period"), (activity ->
                        !periodDomainService.isValidPeriodToCalendarActivity(activity.getPeriod())));

    }


    @Override
    public void hasValidDataToShowOnCalendar(CalendarActivity activity) {

        Set<String> violations = persistRules.entrySet()
                .stream()
                .filter(e -> e.getValue().check(activity))
                .map(Map.Entry::getKey).collect(Collectors.toSet());

        hasErrors(violations).throwing(ValidationException.class);
    }
}
