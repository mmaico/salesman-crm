package br.com.kproj.salesman.infrastructure.helpers;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;

public class DateHelperTest {

    @Test
    public void shouldReturnTrueWhenValidDate() {
        String strValidDate = "06/01/1981";

        Boolean validDate = DateHelper.isValidDate(strValidDate);

        assertThat(validDate, Matchers.is(Boolean.TRUE));
    }

    @Test
    public void shouldReturnFalseWhenInvalidDate() {
        String strValidDate = "06/01";

        Boolean validDate = DateHelper.isValidDate(strValidDate);

        assertThat(validDate, Matchers.is(Boolean.FALSE));
    }

    @Test
    public void shouldConvertStrDateInDate() throws ParseException{
        String strValidDate = "06/01/1981";

        Date dateConverted = DateHelper.convertToDate(strValidDate);

        assertThat(dateConverted, Matchers.is(new SimpleDateFormat("dd/MM/yyyy").parse(strValidDate)));
    }

    @Test
    public void shouldReturnNullWhenInvalidDate() throws ParseException {
        String strValidDate = "06/1981";

        Date dateConverted = DateHelper.convertToDate(strValidDate);

        assertThat(dateConverted, Matchers.nullValue());
    }

}