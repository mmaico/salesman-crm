package br.com.kproj.salesman.assistants.calendar.application.validators;

import br.com.kproj.salesman.assistants.calendar.domain.model.activity.Period;
import br.com.kproj.salesman.assistants.calendar.domain.model.activity.PeriodValidator;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.validators.CheckRule;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.assistants.calendar.application.utils.DateUtils.is;
import static br.com.kproj.salesman.assistants.calendar.application.utils.Greater.greater;
import static br.com.kproj.salesman.assistants.calendar.application.utils.Than.than;
import static br.com.kproj.salesman.infrastructure.helpers.DateHelper.hasHourOrMinutesSet;
import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static br.com.kproj.salesman.infrastructure.model.ConditionalOperator.not;

@Component
public class PeriodBusinessRules implements PeriodValidator {


    private Map<String, CheckRule<Period>> rules = new HashMap<>();
    {
        rules.put("period.without.dates", (period -> period.getStart() == null || period.getEnd() == null));
        rules.put("period.startdate.cannotbe.greaterthan.enddate", (period -> not(is(greater(period.getStart()), than(period.getEnd())))));
        rules.put("period.if.allday.cannothave.hours", (period -> period.isAllDay()
                && (hasHourOrMinutesSet(period.getStart()) || hasHourOrMinutesSet(period.getEnd()))));
    }

    @Override
    public Boolean checkRules(Period period) {

        Set<String> violations = rules.entrySet()
                .stream()
                .filter(e -> e.getValue().check(period))
                .map(Map.Entry::getKey).collect(Collectors.toSet());

        hasErrors(violations).throwing(ValidationException.class);

        return Boolean.TRUE;
    }
}
