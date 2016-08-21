package br.com.kproj.salesman.assistants.calendar.domain;

import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.Period;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.helpers.DateHelper;
import br.com.kproj.salesman.infrastructure.helpers.DateHelper.Greater;
import br.com.kproj.salesman.infrastructure.helpers.DateHelper.Than;
import br.com.kproj.salesman.infrastructure.validators.CheckRuleLegacy;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static br.com.kproj.salesman.infrastructure.helpers.RuleExpressionHelper.description;

@Service
public class PeriodDomainServiceImpl implements PeriodDomainService {


    Map<String, CheckRuleLegacy<Period>> persistRules = new HashMap<>();
    {
        persistRules.put(description("period.without.dates"),
                (period -> period.getStartDate() == null || period.getEndDate() == null));

        persistRules.put(description("period.startdate.cannotbe.greaterthan.enddate"),
                (period -> !DateHelper.is(Greater.create(period.getEndDate()), Than.create(period.getStartDate()))));

        persistRules.put(description("period.if.allday.cannothave.hours"),
                (period -> period.isAllDay()
                                && (DateHelper.hasHourOrMinutesSet(period.getStartDate())
                                || DateHelper.hasHourOrMinutesSet(period.getEndDate())))
                );

    }


    public Boolean isValidPeriodToCalendarActivity(Period period) {

        Set<String> violations = persistRules.entrySet()
                .stream()
                .filter(e -> e.getValue().check(period))
                .map(Map.Entry::getKey).collect(Collectors.toSet());

        hasErrors(violations).throwing(ValidationException.class);

        return Boolean.TRUE;
    }
}
