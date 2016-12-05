package br.com.kproj.salesman.infrastructure.helpers

import spock.lang.Specification


class FilterQueryTest extends Specification {

    def "should build filter by string" () {
        String filterQuery = "has-workers.size.eq(10)"

        def filter = FilterQuery.getFilters(filterQuery)

        expect:
            filter.size == 1
            filter.getAt(0).getName() == "has-workers.size"
            filter.getAt(0).getOperator() == Filter.Operator.EQ
            filter.getAt(0).getObject() == "10"
    }

    def "should ignore query when invalid" () {
        String filterQuery = "has-workers.size.eq"

        def filter = FilterQuery.getFilters(filterQuery)

        expect:
            filter.isEmpty() == true
    }

    def "should build complex query" () {
        String filterQuery = "start.ge(12/06/2016).and(end.le(15/06/2016))" +
                ".or(stats.eq(ok))"

        def filter = FilterQuery.getFilters(filterQuery)

        expect:
            filter.size == 3

            filter.getAt(0).getName() == "start"
            filter.getAt(0).getOperator() == Filter.Operator.GE
            filter.getAt(0).getObject() == "12/06/2016"

            filter.getAt(1) instanceof FilterAnd == Boolean.TRUE
            filter.getAt(1).getName() == "end"
            filter.getAt(1).getOperator() == Filter.Operator.LE
            filter.getAt(1).getObject() == "15/06/2016"

            filter.getAt(2) instanceof FilterOr == Boolean.TRUE
            filter.getAt(2).getName() == "stats"
            filter.getAt(2).getOperator() == Filter.Operator.EQ
            filter.getAt(2).getObject() == "ok"

    }
}
