package br.com.kproj.salesman.register.infrastructure.helpers;


import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.enums.LogActivityTypeEnum;
import br.com.kproj.salesman.infrastructure.entity.timeline.items.LogActivity;
import br.com.kproj.salesman.infrastructure.entity.timeline.items.TimelineActivity;
import br.com.kproj.salesman.infrastructure.helpers.DateHelper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static br.com.kproj.salesman.infrastructure.entity.enums.LogActivityTypeEnum.*;

@Component
public class TimelineActivityHelper {

    private static final String EMAIL_MESSAGE = "escreveu";
    private static final String PHONE_MESSAGE = "conversou por telefone";
    private static final String MEETING_MESSAGE = "tem uma reuniao";
    private static final String NOTE_MESSAGE = "fez uma anotacao";


    private Map<LogActivityTypeEnum, ViewActivity> profile = new HashMap<>();

    {
        profile.put(CALL, ViewActivity.build("telefone", "bg-info", "entypo-phone", PHONE_MESSAGE));
        profile.put(EMAIL, ViewActivity.build("e-mail", "bg-green", "entypo-mail", EMAIL_MESSAGE));
        profile.put(MEETING, ViewActivity.build("visita", "bg-orange", "entypo-location", MEETING_MESSAGE));
        profile.put(NOTE, ViewActivity.build("nota", "bg-purple", "fa fa-edit", NOTE_MESSAGE));

    }

    public ViewActivity getProfile(LogActivity activity) {

        ViewActivity viewActivity = profile.get(activity.getType());

        if (viewActivity == null) {
            return null;
        }

        viewActivity.withDate(DateHelper.convertToString(activity.getCreation()));

        return viewActivity;
    }

    public void orderDesc(List<TimelineActivity> list) {

        if (list == null) {
            return;
        }

        Collections.sort(list, (p1, p2) -> p2.getCreation().compareTo(p1.getCreation()));

    }
    public static class ViewActivity {
        private String date;
        private String type;
        private String color;
        private String icon;
        private String message;
        private User user;

        public ViewActivity(String type, String color, String icon, String message) {
            this.type = type;
            this.color = color;
            this.icon = icon;
            this.message = message;
        }

        public String getDate() {
            return date;
        }

        public ViewActivity withDate(String date) {
            this.date = date;
            return this;
        }

        public ViewActivity withUser(User user) {
            this.user= user;
            return this;
        }

        public String getType() {
            return type;

        }

        public String getColor() {
            return color;
        }

        public String getIcon() {
            return icon;
        }

        public String getMessage() {
            return message;
        }

        public User getUser() {
            return user;
        }

        public ViewActivity withMessage(String mensage) {
            this.message = mensage;
            return this;
        }

        public static ViewActivity build(String type, String color, String icon, String message) {
            return new ViewActivity(type, color, icon, message);
        }
    }
}
