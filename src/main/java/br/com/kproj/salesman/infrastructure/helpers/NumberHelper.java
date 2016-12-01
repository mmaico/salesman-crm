package br.com.kproj.salesman.infrastructure.helpers;


import java.math.BigDecimal;

public class NumberHelper {

    public static Boolean isNotNegativeNumber(BigDecimal base) {

        if (base == null) {
            return Boolean.FALSE;
        }

        return base.compareTo(BigDecimal.ZERO) == 0 || base.compareTo(BigDecimal.ZERO) > 0;
    }

    public static Boolean isNegativeNumber(BigDecimal base) {

        if (base == null) {
            return Boolean.TRUE;
        }

        return base.compareTo(BigDecimal.ZERO) < 0;
    }
    
    public static Boolean isNumberGreaterThanZero(BigDecimal base) {

        if (base == null) {
            return Boolean.FALSE;
        }

        return base.compareTo(BigDecimal.ZERO) > 0;
    }
    
    public static Boolean isNumberEqualsZero(BigDecimal base) {

        if (base == null) {
            return Boolean.FALSE;
        }

        return base.compareTo(BigDecimal.ZERO) == 0;
    }
    
    public static Boolean isEquals(BigDecimal value, BigDecimal valueTwo) {
    	value = value == null ? BigDecimal.ZERO: value;
    	valueTwo = valueTwo == null ? BigDecimal.ZERO: valueTwo;
    	
    	
    	return value.compareTo(valueTwo) == 0;
    }
    
}
