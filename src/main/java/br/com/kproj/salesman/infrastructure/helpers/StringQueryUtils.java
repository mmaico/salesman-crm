package br.com.kproj.salesman.infrastructure.helpers;


import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

public class StringQueryUtils {


    public static Integer count(String value, String... separators) {

        return Lists.newArrayList(separators).stream()
                .mapToInt(separator -> StringUtils.countMatches(value, separator))
                .sum();
    }

    /**
     *
     * recupera valores dentro de parenteses exemplo:
     *  Entrada: start.ge(12/06/2016)
     *  Retorno: 12/06/2016
     *
     *  Entrada: and(end.le(15/06/2016))
     *  Retorno: end.le(15/06/2016)
     *
     */
    public static String getQueryValue(String filterQuery) {
        int startPositionValue = StringUtils.indexOf(filterQuery, "(");
        int endPositionValue = StringUtils.lastIndexOf(filterQuery, ")");

        if (startPositionValue < 0 || endPositionValue < 0) return StringUtils.EMPTY;

        return filterQuery.substring(startPositionValue + 1, endPositionValue);
    }

    public static FilterQuery.LogicalOperator getLogicalOperator(String query) {
        if (query.startsWith("and(")) {
            return FilterQuery.LogicalOperator.AND;
        } else if (query.startsWith("or(")) {
            return FilterQuery.LogicalOperator.OR;
        } else {
            return FilterQuery.LogicalOperator.EMPTY;
        }
    }

    public static class Token {
        private String value;
        private String separator;

    }
}
