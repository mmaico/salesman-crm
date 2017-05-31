package br.com.kproj.salesman.infrastructure.helpers;

import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;


public class FormatMoneyHelperTest {

    @Test
    public void shouldConvertAMoneyToLong() {
        BigDecimal total = new BigDecimal(100);

        Long totalLong = FormatMoneyHelper.formatMoneyToLong(total);

        assertThat(totalLong, equalTo(10000l));
    }

    @Test
    public void shouldConvertAMoneyToLongWithCents() {
        BigDecimal total = new BigDecimal(100.45);

        Long totalLong = FormatMoneyHelper.formatMoneyToLong(total);

        assertThat(totalLong, equalTo(10045l));
    }

    @Test
    public void shouldFormatMoney() {
        BigDecimal total = new BigDecimal(100.45);

        String result = FormatMoneyHelper.formatMoney(Language.EN, total);

        assertThat(result, equalTo("USD100.45"));
    }

    @Test
    public void shouldFormatMoneyDefault() {
        BigDecimal total = new BigDecimal(100.45);

        String result = FormatMoneyHelper.formatMoney(total);

        assertThat(result, equalTo("R$ 100,45"));
    }

    @Test
    public void shouldFormatMoneyPT() {
        BigDecimal total = new BigDecimal(100.45);

        String result = FormatMoneyHelper.formatMoney(Language.PT, total);

        assertThat(result, equalTo("R$ 100,45"));
    }

    @Test
    public void shouldFormat2MoneyDefautl() {
        BigDecimal total = new BigDecimal(100.45);

        String result = FormatMoneyHelper.formatMoney2(total);

        assertThat(result, equalTo("100,45"));
    }

    @Test
    public void shouldFormat2MoneyPT() {
        BigDecimal total = new BigDecimal(100.45);

        String result = FormatMoneyHelper.formatMoney2(Language.PT, total);

        assertThat(result, equalTo("100,45"));
    }

    @Test
    public void shouldFormat2MoneyEN() {
        BigDecimal total = new BigDecimal(100.45);

        String result = FormatMoneyHelper.formatMoney2(Language.EN, total);

        assertThat(result, equalTo("100.45"));
    }
}