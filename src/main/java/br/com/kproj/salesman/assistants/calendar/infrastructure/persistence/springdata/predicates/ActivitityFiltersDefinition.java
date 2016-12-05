package br.com.kproj.salesman.assistants.calendar.infrastructure.persistence.springdata.predicates;

import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.QCalendarActivityEntity;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.helpers.DateHelper;
import br.com.kproj.salesman.infrastructure.helpers.Filter;
import br.com.kproj.salesman.infrastructure.helpers.FilterAggregator;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.EntityPathBase;
import org.springframework.stereotype.Component;

import java.util.Date;
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
            return qActivity.startDate.goe(convertToISO8601(filter.getObject().toString()));
        });

        startDatePredicate.put(Filter.Operator.LE, (filter, qEntity) -> {
            QCalendarActivityEntity  qActivity = (QCalendarActivityEntity) qEntity;
            return qActivity.startDate.loe(convertToISO8601(filter.getObject().toString()));
        });

        endDatePredicate.put(Filter.Operator.GE, (filter, qEntity) -> {
            QCalendarActivityEntity  qActivity = (QCalendarActivityEntity) qEntity;
            return qActivity.endDate.goe(convertToISO8601(filter.getObject().toString()));
        });

        endDatePredicate.put(Filter.Operator.LE, (filter, qEntity) -> {
            QCalendarActivityEntity  qActivity = (QCalendarActivityEntity) qEntity;
            return qActivity.startDate.goe(convertToISO8601(filter.getObject().toString()));
        });

        expressions.put("startDate", startDatePredicate);
        expressions.put("endDate", startDatePredicate);

    }

    public BooleanBuilder buildCriteria(FilterAggregator aggregator, QCalendarActivityEntity qActivity) {

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        aggregator.getFilters().forEach(filter -> {
            Map<Filter.Operator, PredicateOperation> result = expressions.getOrDefault(filter.getName(), new HashMap<>());

            if (result.isEmpty()) {
                throw new IllegalArgumentException("Invalid filter: " + filter.getName());
            }

            PredicateOperation operation = result.get(filter.getOperator());

            if (operation == null) {
                throw new ValidationException("operation.not.supported");
            }
            BooleanExpression condition = result.get(filter.getOperator()).get(filter, qActivity);
            booleanBuilder.and(condition);
        });

        return booleanBuilder;
    }

    private interface PredicateOperation {

        BooleanExpression get(Filter filter, EntityPathBase deliveryEntity);
    }
}
