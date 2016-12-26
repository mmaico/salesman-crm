package br.com.kproj.salesman.assistants.calendar.activities.activity.infrastructure.persistence.springdata.predicates;

import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.QCalendarActivityEntity;
import br.com.kproj.salesman.infrastructure.helpers.Filter;
import br.com.kproj.salesman.infrastructure.helpers.FilterAggregator;
import br.com.kproj.salesman.infrastructure.repository.support.BuildCriteria;
import br.com.kproj.salesman.infrastructure.repository.support.PredicateOperation;
import com.querydsl.core.BooleanBuilder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static br.com.kproj.salesman.infrastructure.helpers.DateHelper.convertToISO8601;

@Component
public class ActivitityFiltersDefinition {

    private static Map<String, Map<Filter.Operator, PredicateOperation>> expressions = new HashMap<>();

    private static Map<Filter.Operator, PredicateOperation> startDatePredicate = new HashMap<>();
    private static Map<Filter.Operator, PredicateOperation> endDatePredicate = new HashMap<>();

    static {
        startDatePredicate.put(Filter.Operator.GE, (filter, qEntity) -> {
            QCalendarActivityEntity  qActivity = (QCalendarActivityEntity) qEntity;
            return qActivity.start.goe(convertToISO8601(filter.getObject().toString()));
        });

        startDatePredicate.put(Filter.Operator.LE, (filter, qEntity) -> {
            QCalendarActivityEntity  qActivity = (QCalendarActivityEntity) qEntity;
            return qActivity.start.loe(convertToISO8601(filter.getObject().toString()));
        });

        endDatePredicate.put(Filter.Operator.GE, (filter, qEntity) -> {
            QCalendarActivityEntity  qActivity = (QCalendarActivityEntity) qEntity;
            return qActivity.end.goe(convertToISO8601(filter.getObject().toString()));
        });

        endDatePredicate.put(Filter.Operator.LE, (filter, qEntity) -> {
            QCalendarActivityEntity  qActivity = (QCalendarActivityEntity) qEntity;
            return qActivity.end.goe(convertToISO8601(filter.getObject().toString()));
        });

        expressions.put("start", startDatePredicate);
        expressions.put("end", startDatePredicate);

    }

    public BooleanBuilder buildCriteria(FilterAggregator aggregator, QCalendarActivityEntity qActivity) {
        return BuildCriteria.build(aggregator, qActivity, expressions);
    }

}
