package br.com.kproj.salesman.assistants.calendar.domain.model.activity;


import br.com.kproj.salesman.assistants.calendar.domain.model.calendar.Calendar;
import br.com.kproj.salesman.assistants.calendar.domain.model.user.User;
import br.com.kproj.salesman.infrastructure.model.ValueObject;

public class ActivityInCalendar implements ValueObject {

    private final Activity activity;
    private final Calendar calendar;
    private final User user;

    public ActivityInCalendar(Activity activity, Calendar calendar, User user) {
        this.activity = activity;
        this.calendar = calendar;
        this.user = user;
    }

    public Activity getActivity() {
        return activity;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public User getUser() {
        return user;
    }

    public static ActivityInCalendar create(Activity activity, Calendar calendar, User user) {
        return new ActivityInCalendar(activity, calendar, user);
    }
}
