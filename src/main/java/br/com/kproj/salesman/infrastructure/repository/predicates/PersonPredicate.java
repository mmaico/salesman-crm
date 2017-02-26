package br.com.kproj.salesman.infrastructure.repository.predicates;

import br.com.kproj.salesman.infrastructure.entity.person.PersonProfile;
import br.com.kproj.salesman.infrastructure.entity.person.QPerson;
import br.com.kproj.salesman.infrastructure.helpers.Filter;
import br.com.kproj.salesman.infrastructure.helpers.FilterAggregator;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.util.Collection;

public class PersonPredicate {

	public static Predicate findByFilters(FilterAggregator filter) {
		QPerson qPerson = QPerson.person;
		
		BooleanExpression expression = null;
		
		Filter<PersonProfile> profileFilter = filter.findFilter("profile");
		Filter<Collection<PersonProfile>> profilesFilter = filter.findFilter("profiles");
		Filter<Boolean> statusFilter = filter.findFilter("status");
		
		if (!profileFilter.isNullObject()) {
			expression = expression.and(qPerson.profile.eq(profileFilter.getObject()));
		}
		
		if (!profilesFilter.isNullObject()) {
            expression = expression.and(qPerson.profile.in(profilesFilter.getObject()));
		}
		
		if (!statusFilter.isNullObject()) {
			expression = expression.and(qPerson.active.eq(statusFilter.getObject()));
		}
		
		return expression;
	}
	
	public static OrderSpecifier<String> orderByName() {
		return QPerson.person.name.asc();
	}
}

