package br.com.kproj.salesman.calendar.domain;

import br.com.kproj.salesman.calendar.infrastructure.CalendarActivityRepository;
import br.com.kproj.salesman.infrastructure.entity.calendar.CalendarActivity;
import br.com.kproj.salesman.infrastructure.entity.calendar.Period;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.helpers.DateHelper;
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
public class PeriodDomainServiceImpl implements PeriodDomainService {

    @Autowired
    private CalendarActivityRepository repository;

    Map<String, CheckRule<Period>> persistRules = new HashMap<>();
    {
        persistRules.put(description("calendar.activity.period.without.dates"),
                (period -> period.getStartDate() == null || period.getEndDate() == null));

        persistRules.put(description("calendar.activity.period.startdate.cannotbe.greaterthan.enddate"),
                (period -> !DateHelper.is(DateHelper.Greater.create(period.getEndDate()),
                            DateHelper.Than.create(period.getStartDate()))));


    }


    @Override
    public void checkBusinessRulesFor(Period period) {

        Set<String> violations = persistRules.entrySet()
                .stream()
                .filter(e -> e.getValue().check(period))
                .map(Map.Entry::getKey).collect(Collectors.toSet());

        hasErrors(violations).throwing(ValidationException.class);
    }
}
