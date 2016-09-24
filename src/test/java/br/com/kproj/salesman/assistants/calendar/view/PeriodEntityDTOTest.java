package br.com.kproj.salesman.assistants.calendar.view;

import org.joda.time.DateTime;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class PeriodEntityDTOTest {


    @Test
    public void shouldReturnStartDateWithHour() {
        PeriodDTO periodDTO = new PeriodDTO();
        periodDTO.setStartDate("10/05/2016");
        periodDTO.setStartHour("10:30");

        Date resultDate = periodDTO.getFullStartDate();
        DateTime resultDateTime = new DateTime(resultDate);

        assertThat(resultDateTime.getYear(), is(2016));
        assertThat(resultDateTime.getMonthOfYear(), is(5));
        assertThat(resultDateTime.getDayOfMonth(), is(10));
        assertThat(resultDateTime.getHourOfDay(), is(10));
        assertThat(resultDateTime.getMinuteOfHour(), is(30));
    }

    @Test
    public void shouldReturnAndDateWithHour() {
        PeriodDTO periodDTO = new PeriodDTO();
        periodDTO.setEndDate("11/08/2017");
        periodDTO.setEndHour("15:30");

        Date resultDate = periodDTO.getFullEndDate();
        DateTime resultDateTime = new DateTime(resultDate);

        assertThat(resultDateTime.getYear(), is(2017));
        assertThat(resultDateTime.getMonthOfYear(), is(8));
        assertThat(resultDateTime.getDayOfMonth(), is(11));
        assertThat(resultDateTime.getHourOfDay(), is(15));
        assertThat(resultDateTime.getMinuteOfHour(), is(30));
    }

    @Test
    public void shouldIgnoreHourWhenInformedAllDayOnStartDate() {

        PeriodDTO periodDTO = new PeriodDTO();
        periodDTO.setStartDate("20/09/2018");
        periodDTO.setStartHour("21:15");
        periodDTO.setAllDay(Boolean.TRUE);

        Date resultDate = periodDTO.getFullStartDate();
        DateTime resultDateTime = new DateTime(resultDate);

        assertThat(resultDateTime.getYear(), is(2018));
        assertThat(resultDateTime.getMonthOfYear(), is(9));
        assertThat(resultDateTime.getDayOfMonth(), is(20));
        assertThat(resultDateTime.getHourOfDay(), is(0));
        assertThat(resultDateTime.getMinuteOfHour(), is(0));
    }

    @Test
    public void shouldIgnoreHourWhenInformedAllDayOnEndDate() {

        PeriodDTO periodDTO = new PeriodDTO();
        periodDTO.setEndDate("21/10/2019");
        periodDTO.setEndHour("22:16");
        periodDTO.setAllDay(Boolean.TRUE);

        Date resultDate = periodDTO.getFullEndDate();
        DateTime resultDateTime = new DateTime(resultDate);

        assertThat(resultDateTime.getYear(), is(2019));
        assertThat(resultDateTime.getMonthOfYear(), is(10));
        assertThat(resultDateTime.getDayOfMonth(), is(21));
        assertThat(resultDateTime.getHourOfDay(), is(0));
        assertThat(resultDateTime.getMinuteOfHour(), is(0));
    }



}