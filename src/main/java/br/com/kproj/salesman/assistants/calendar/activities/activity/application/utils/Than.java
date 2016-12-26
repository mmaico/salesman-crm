package br.com.kproj.salesman.assistants.calendar.activities.activity.application.utils;


import java.util.Date;

public class Than {

    private Date date;

    private Than(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public static Than than(Date date) {
        return new Than(date);
    }
}
