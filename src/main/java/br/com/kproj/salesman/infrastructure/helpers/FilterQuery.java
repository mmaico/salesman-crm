package br.com.kproj.salesman.infrastructure.helpers;


import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static br.com.kproj.salesman.infrastructure.helpers.FilterQuery.LogicalOperator.*;
import static br.com.kproj.salesman.infrastructure.helpers.StringQueryUtils.getQueryValue;

public class FilterQuery {

    public enum LogicalOperator {
        AND, OR, EMPTY
    }

    private static Map<LogicalOperator, FilterBuild> filterStrategy = new HashMap<>();

    static {
        filterStrategy.put(AND, ((expression, operator, value) ->
            FilterAnd.build(expression, Filter.Operator.valueOf(operator.toUpperCase()), value)
        ));

        filterStrategy.put(OR, ((expression, operator, value) ->
            FilterOr.build(expression, Filter.Operator.valueOf(operator.toUpperCase()), value)
        ));

        filterStrategy.put(EMPTY, ((expression, operator, value) ->
            Filter.build(expression, Filter.Operator.valueOf(operator.toUpperCase()), value)
        ));
    }

    /**
     * Metodo responsavel por converter uma queryString em uma lista de filtros
     *
     * Exemplo: start.ge(12/06/2016).and(end.le(15/06/2016)).or(status.eq(ok))
     *
     * Retornara 3 filtros como os seguintes:
     *  Filter 1: object: 12/06/2016, name:start, operator:ge
     *  FilterAnd 2: object: 15/06/2016, name:end, operator:le
     *  FilterOr 3: object: ok, name:status, operator:eq
     */
    public static Collection<Filter> getFilters(String filterQuery) {
        try {
            int quantityLogicalOperatorsPresent = StringQueryUtils.count(filterQuery, ".and", ".or");
            Collection<Filter> filters = Lists.newArrayList();

            for (int j = 0; j <= quantityLogicalOperatorsPresent; j++) {
                String[] split = filterQuery.split("\\)\\.");
                LogicalOperator logical = StringQueryUtils.getLogicalOperator(split[j]);
                Filter filter = (split.length - 1) == j
                        ? generateFilters(split[j], logical)
                        : generateFilters(split[j] + ")", logical);

                if(!filter.isNullObject()) filters.add(filter);
            }
            return filters;
        } catch (Exception e) {
            return Lists.newArrayList();
        }
    }

    private static Filter generateFilters(String filterQuery, LogicalOperator logicalOperator) {
        try {
            String mainQuery = !EMPTY.equals(logicalOperator) ? getQueryValue(filterQuery) : filterQuery;

            int startValue = StringUtils.indexOf(mainQuery, "(");
            int endValue = StringUtils.indexOf(mainQuery, ")");
            int endExpression = StringUtils.lastIndexOf(mainQuery.substring(0, startValue), ".");

            String expression = mainQuery.substring(0, endExpression);
            String operator = mainQuery.substring(endExpression + 1, startValue);
            String value = mainQuery.substring(startValue + 1, endValue);

            return filterStrategy.get(logicalOperator).build(expression, operator, value);
        } catch (Exception e) {
            return Filter.nullObject();
        }
    }

    private interface FilterBuild {

        Filter build(String expression, String operator, String value);
    }
}
