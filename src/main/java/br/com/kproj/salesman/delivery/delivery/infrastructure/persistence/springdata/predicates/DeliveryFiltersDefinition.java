package br.com.kproj.salesman.delivery.delivery.infrastructure.persistence.springdata.predicates;

import br.com.kproj.salesman.infrastructure.entity.delivery.QDeliveryEntity;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.helpers.Filter;
import br.com.kproj.salesman.infrastructure.helpers.FilterAggregator;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DeliveryFiltersDefinition {

    private Map<String, Map<Filter.Operator, PredicateOperation>> expressions = new HashMap<>();

    private Map<Filter.Operator, PredicateOperation> workersPredicate = new HashMap<>();

    {
        workersPredicate.put(Filter.Operator.GT, (filter, deliveryEntity) ->
                deliveryEntity.workers.size().gt(NumberUtils.toInt(filter.getObject().toString()))
        );

        workersPredicate.put(Filter.Operator.LT, (filter, deliveryEntity) ->
                deliveryEntity.workers.size().lt(NumberUtils.toInt(filter.getObject().toString()))
        );

        workersPredicate.put(Filter.Operator.EQ, (filter, deliveryEntity) ->
                deliveryEntity.workers.size().eq(NumberUtils.toInt(filter.getObject().toString()))
        );

        expressions.put("has-workers.size", workersPredicate);

    }

    public BooleanBuilder buildCriteria(FilterAggregator aggregator, QDeliveryEntity qDeliveryEntity) {

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
            BooleanExpression condition = result.get(filter.getOperator()).get(filter, qDeliveryEntity);
            booleanBuilder.and(condition);
        });

        return booleanBuilder;
    }

    private interface PredicateExpression {

        void get(Filter filter, QDeliveryEntity deliveryEntity);
    }

    private interface PredicateOperation {

        BooleanExpression get(Filter filter, QDeliveryEntity deliveryEntity);
    }
}
