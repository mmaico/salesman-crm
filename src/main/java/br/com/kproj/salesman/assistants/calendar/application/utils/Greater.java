package br.com.kproj.salesman.assistants.calendar.application.utils;

import java.util.Date;


public class Greater {

    private final Date date;

    private Greater(Date date) {
        this.date = date;

    }

    public Date getDate() {
        return date;
    }

    public static Greater greater(Date date) {
        return new Greater(date);
    }
}
