package br.com.kproj.salesman.assistants.calendar.activities.activity.domain.model.activity;


import br.com.kproj.salesman.assistants.calendar.activities.activity.domain.model.calendar.Calendar;
import br.com.kproj.salesman.infrastructure.model.ValueObject;


public class NewActivity implements ValueObject {

    private final Activity activity;
    private final Calendar calendar;

    public NewActivity(Activity activity, Calendar calendar) {
        this.activity = activity;
        this.calendar = calendar;
    }

    public Activity getActivity() {
        return activity;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public static NewActivity newActivity(Calendar calendar, Activity activity) {
        return new NewActivity(activity, calendar);
    }
}
