package br.com.kproj.salesman.infrastructure.repository.support;

import br.com.kproj.salesman.infrastructure.helpers.Filter;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.EntityPathBase;

@FunctionalInterface
public interface PredicateOperation {

    BooleanExpression get(Filter filter, EntityPathBase deliveryEntity);
}
