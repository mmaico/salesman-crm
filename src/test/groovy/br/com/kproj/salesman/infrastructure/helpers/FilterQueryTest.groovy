package br.com.kproj.salesman.infrastructure.helpers

import spock.lang.Specification


class FilterQueryTest extends Specification {

    def "should build filter by string" () {
        String filterQuery = "has-workers.size.eq(10)"

        def filter = FilterQuery.generateFilters(filterQuery)

        expect:
            filter.getName() == "has-workers.size"
            filter.getOperator() == Filter.Operator.EQ
            filter.getObject() == "10"
    }

    def "should ignore query when invalid" () {
        String filterQuery = "has-workers.size.eq"

        def filter = FilterQuery.generateFilters(filterQuery)

        expect:
            filter.isNullObject() == true
    }
}
