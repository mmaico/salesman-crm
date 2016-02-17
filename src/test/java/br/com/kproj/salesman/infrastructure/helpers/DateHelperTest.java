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
import static org.hamcrest.Matchers.equalTo;
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

    @Test
    public void shouldReturnTrueWhenDateHaveHour() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        LocalDateTime dateTime = LocalDateTime.parse("05/02/2015 10:00", formatter);

        Date dateOne = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());

        Boolean result = DateHelper.hasHourOrMinutesSet(dateOne);

        assertThat(result, is(Boolean.TRUE));
    }

    @Test
    public void shouldReturnTrueWhenDateHaveMinutes() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        LocalDateTime dateTime = LocalDateTime.parse("05/02/2015 00:01", formatter);

        Date dateOne = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());

        Boolean result = DateHelper.hasHourOrMinutesSet(dateOne);

        assertThat(result, is(Boolean.TRUE));
    }

    @Test
    public void shouldReturnFalseWhenDateHaveMinutesAndHoursWithZero() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        LocalDateTime dateTime = LocalDateTime.parse("05/02/2015 00:00", formatter);

        Date dateOne = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());

        Boolean result = DateHelper.hasHourOrMinutesSet(dateOne);

        assertThat(result, is(Boolean.FALSE));
    }

    @Test
    public void shouldReturn2WeekBetweenDates() throws ParseException {
        Date firstDate = new SimpleDateFormat("dd/MM/yyyy").parse("01/02/2012");
        Date lastDate = new SimpleDateFormat("dd/MM/yyyy").parse("14/02/2012");

        Integer quantityWeeks = DateHelper.quantityWeeksBetween(firstDate, lastDate);

        assertThat(quantityWeeks, Matchers.is(2));
    }

    @Test
    public void shouldReturnZeroIfNotHaveWeekBetweenDates() throws ParseException {
        Date firstDate = new SimpleDateFormat("dd/MM/yyyy").parse("01/02/2012");
        Date lastDate = new SimpleDateFormat("dd/MM/yyyy").parse("05/02/2012");

        Integer quantityWeeks = DateHelper.quantityWeeksBetween(firstDate, lastDate);

        assertThat(quantityWeeks, Matchers.is(0));
    }

    @Test
    public void shouldReturnCorrectlyFirstWeekOfTheRange() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date firstDate = dateFormat.parse("01/02/2012");
        Date lastDate = dateFormat.parse("26/02/2012");

        RangeDateDTO rangeDate = DateHelper.getRangeWeek(firstDate, lastDate, 1);

        assertThat(dateFormat.format(rangeDate.getStartDate()), equalTo("01/02/2012"));
        assertThat(dateFormat.format(rangeDate.getEndDate()), equalTo("07/02/2012"));
    }

    @Test
    public void shouldReturnTheRangeDateOfWeek() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date firstDate = dateFormat.parse("01/02/2012");
        Date lastDate = dateFormat.parse("26/02/2012");

        RangeDateDTO rangeDate = DateHelper.getRangeWeek(firstDate, lastDate, 2);

        assertThat(dateFormat.format(rangeDate.getStartDate()), equalTo("08/02/2012"));
        assertThat(dateFormat.format(rangeDate.getEndDate()), equalTo("14/02/2012"));
    }

    @Test
    public void shouldReturnDates() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date firstDate = dateFormat.parse("01/02/2012");
        Date lastDate = dateFormat.parse("26/02/2012");

        RangeDateDTO rangeDate = DateHelper.getRangeWeek(firstDate, lastDate, 3);

        assertThat(dateFormat.format(rangeDate.getStartDate()), equalTo("15/02/2012"));
        assertThat(dateFormat.format(rangeDate.getEndDate()), equalTo("21/02/2012"));
    }

    @Test
    public void shouldNotReturnRangeDateWithEndDateGreaterLastDate() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date firstDate = dateFormat.parse("01/02/2012");
        Date lastDate = dateFormat.parse("26/02/2012");

        RangeDateDTO rangeDate = DateHelper.getRangeWeek(firstDate, lastDate, 4);

        assertThat(dateFormat.format(rangeDate.getStartDate()), equalTo("22/02/2012"));
        assertThat(dateFormat.format(rangeDate.getEndDate()), equalTo("26/02/2012"));
    }

    @Test
    public void shouldReturnNullObjectIfWeekRangeNotExist() throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date firstDate = dateFormat.parse("01/02/2012");
        Date lastDate = dateFormat.parse("26/02/2012");

        RangeDateDTO rangeDate = DateHelper.getRangeWeek(firstDate, lastDate, 5);

        assertThat(rangeDate.isNullObject(), Matchers.is(Boolean.TRUE));

    }
}