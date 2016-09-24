package br.com.kproj.salesman.assistants.calendar.application.utils;


import org.joda.time.LocalDateTime;

import java.util.Date;

public class DateUtils {

    public static Boolean is(Greater greater, Than than) {
        return greater.getDate().after(than.getDate());
    }

    public static Boolean hasHourOrMinutesSet(Date date) {
        LocalDateTime localDateTime = LocalDateTime.fromDateFields(date);
        int hourOfDay = localDateTime.getHourOfDay();
        int minuteOfHour = localDateTime.getMinuteOfHour();

        return hourOfDay > 0 || minuteOfHour > 0;
    }
}
