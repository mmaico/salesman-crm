package br.com.kproj.salesman.infrastructure.helpers;


import org.apache.commons.lang3.StringUtils;

public class FilterQuery {

    public static Filter generateFilters(String filterQuery) {
        try {
            int startValue = StringUtils.indexOf(filterQuery, "(");
            int endExpression = StringUtils.lastIndexOf(filterQuery.substring(0, startValue), ".");

            String expression = filterQuery.substring(0, endExpression);
            String operator = filterQuery.substring(endExpression + 1, startValue);
            String value = filterQuery.substring(startValue + 1, filterQuery.length() - 1);

            return Filter.build(expression, Filter.Operator.valueOf(operator.toUpperCase()), value);
        } catch (Exception e) {
            return Filter.nullObject();
        }
    }
}
