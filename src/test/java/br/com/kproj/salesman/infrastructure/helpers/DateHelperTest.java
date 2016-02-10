package br.com.kproj.salesman.infrastructure.helpers;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

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

    @Test
    public void shouldReturnTrueWhenADateIsBiggerThanOther() {
        Date dateOne = DateHelper.convertToDate("06/02/2015");
        Date dateTwo = DateHelper.convertToDate("05/02/2015");

        Boolean result = DateHelper.is(DateHelper.Greater.create(dateOne), DateHelper.Than.create(dateTwo));

        assertThat(result, is(Boolean.TRUE));
    }

    @Test
    public void shouldReturnTrueWhenIsBiggerOnlyHoursThanOther() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        LocalDateTime dateTime = LocalDateTime.parse("05/02/2015 10:20", formatter);
        LocalDateTime dateTimeTwo = LocalDateTime.parse("05/02/2015 09:20", formatter);
        Date dateOne = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
        Date dateTwo = Date.from(dateTimeTwo.atZone(ZoneId.systemDefault()).toInstant());

        Boolean result = DateHelper.is(DateHelper.Greater.create(dateOne), DateHelper.Than.create(dateTwo));

        assertThat(result, is(Boolean.TRUE));
    }

    @Test
    public void shouldReturnTrueWhenIsBiggerOnlyMinutesThanOther() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        LocalDateTime dateTime = LocalDateTime.parse("05/02/2015 10:21", formatter);
        LocalDateTime dateTimeTwo = LocalDateTime.parse("05/02/2015 10:20", formatter);
        Date dateOne = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
        Date dateTwo = Date.from(dateTimeTwo.atZone(ZoneId.systemDefault()).toInstant());

        Boolean result = DateHelper.is(DateHelper.Greater.create(dateOne), DateHelper.Than.create(dateTwo));

        assertThat(result, is(Boolean.TRUE));
    }
}