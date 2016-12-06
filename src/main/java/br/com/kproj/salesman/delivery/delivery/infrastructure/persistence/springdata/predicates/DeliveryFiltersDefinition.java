package br.com.kproj.salesman.delivery.delivery.infrastructure.persistence.springdata.predicates;

import br.com.kproj.salesman.infrastructure.entity.delivery.QDeliveryEntity;
import br.com.kproj.salesman.infrastructure.helpers.Filter;
import br.com.kproj.salesman.infrastructure.helpers.FilterAggregator;
import br.com.kproj.salesman.infrastructure.repository.support.BuildCriteria;
import br.com.kproj.salesman.infrastructure.repository.support.PredicateOperation;
import com.querydsl.core.BooleanBuilder;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DeliveryFiltersDefinition {

    private static Map<String, Map<Filter.Operator, PredicateOperation>> expressions = new HashMap<>();

    private static Map<Filter.Operator, PredicateOperation> workersPredicate = new HashMap<>();

    static {
        workersPredicate.put(Filter.Operator.GT, (filter, qEntity) -> {
            QDeliveryEntity deliveryEntity =  (QDeliveryEntity) qEntity;
            return deliveryEntity.workers.size().gt(NumberUtils.toInt(filter.getObject().toString()));
        });

        workersPredicate.put(Filter.Operator.LT, (filter, qEntity) -> {
            QDeliveryEntity deliveryEntity =  (QDeliveryEntity) qEntity;
            return deliveryEntity.workers.size().lt(NumberUtils.toInt(filter.getObject().toString()));
        });

        workersPredicate.put(Filter.Operator.EQ, (filter, qEntity) -> {
            QDeliveryEntity deliveryEntity =  (QDeliveryEntity) qEntity;
            return deliveryEntity.workers.size().eq(NumberUtils.toInt(filter.getObject().toString()));
        });

        expressions.put("has-workers.size", workersPredicate);
    }

    public BooleanBuilder buildCriteria(FilterAggregator aggregator, QDeliveryEntity qDeliveryEntity) {

        return BuildCriteria.build(aggregator, qDeliveryEntity, expressions);

    }

}
