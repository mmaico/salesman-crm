package br.com.kproj.salesman.infrastructure.helpers;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class NumberHelperTest {


    @Test
    public void shouldReturnTrueWhenValueGreaterOrEqualsZero() {
        BigDecimal value = BigDecimal.ZERO;

        Boolean notNegativeNumber = NumberHelper.isNotNegativeNumber(value);

        assertThat(notNegativeNumber, Matchers.is(Boolean.TRUE));
    }

    @Test
    public void shouldReturnFalseWhenValueLessThanZero() {
        BigDecimal value = new BigDecimal("-12.0");

        Boolean notNegativeNumber = NumberHelper.isNotNegativeNumber(value);

        assertThat(notNegativeNumber, Matchers.is(Boolean.FALSE));
    }
    
    @Test
    public void shouldReturnTrueWhenValueIsGreaterThanZero() {
        BigDecimal value = BigDecimal.TEN;

        Boolean numberGreaterZero = NumberHelper.isNumberGreaterThanZero(value);

        assertThat(numberGreaterZero, Matchers.is(Boolean.TRUE));
    }
    
    @Test
    public void shouldReturnFalseWhenValueIsZero() {
        BigDecimal value = BigDecimal.ZERO;

        Boolean numberGreaterZero = NumberHelper.isNumberGreaterThanZero(value);

        assertThat(numberGreaterZero, Matchers.is(Boolean.FALSE));
    }


}