package br.com.kproj.salesman.infrastructure.repository.support;


import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.helpers.Filter;
import br.com.kproj.salesman.infrastructure.helpers.FilterAggregator;
import br.com.kproj.salesman.infrastructure.helpers.FilterOr;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.EntityPathBase;

import java.util.HashMap;
import java.util.Map;

public class BuildCriteria {


    public static BooleanBuilder build(FilterAggregator aggregator, EntityPathBase deliveryEntity,
                                       Map<String, Map<Filter.Operator, PredicateOperation>> expressions) {


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

            BooleanExpression condition = result.get(filter.getOperator()).get(filter, deliveryEntity);

            if (filter instanceof FilterOr) {
                booleanBuilder.or(condition);
            } else {
                booleanBuilder.and(condition);
            }
        });

        return booleanBuilder;

    }
}
