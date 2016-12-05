package br.com.kproj.salesman.assistants.calendar.domain.model.activity;


import br.com.kproj.salesman.assistants.calendar.domain.model.calendar.Calendar;
import br.com.kproj.salesman.infrastructure.model.ValueObject;

public class ActivityInCalendar implements ValueObject {

    private final Activity activity;
    private final Calendar calendar;

    public ActivityInCalendar(Activity activity, Calendar calendar) {
        this.activity = activity;
        this.calendar = calendar;
    }

    public Activity getActivity() {
        return activity;
    }

    public Calendar getCalendar() {
        return calendar;
    }


    public static ActivityInCalendar create(Activity activity, Calendar calendar) {
        return new ActivityInCalendar(activity, calendar);
    }
}
