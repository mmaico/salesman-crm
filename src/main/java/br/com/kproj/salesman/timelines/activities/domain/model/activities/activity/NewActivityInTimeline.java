package br.com.kproj.salesman.timelines.activities.domain.model.activities.activity;


import br.com.kproj.salesman.timelines.activities.domain.model.timeline.Timeline;
import br.com.kproj.salesman.timelines.activities.domain.model.user.User;

public class NewActivityInTimeline {

    private final Activity activity;
    private final Timeline timeline;
    private final User user;

    public NewActivityInTimeline(Activity activity, Timeline timeline, User user) {
        this.activity = activity;
        this.timeline = timeline;
        this.user = user;
    }

    public Activity getActivity() {
        return activity;
    }

    public Timeline getTimeline() {
        return timeline;
    }

    public User getUser() {
        return user;
    }

    public static NewActivityInTimeline newActivity(Activity activity, Timeline timeline,
                                                    User user) {
        return new NewActivityInTimeline(activity, timeline, user);
    }

}
