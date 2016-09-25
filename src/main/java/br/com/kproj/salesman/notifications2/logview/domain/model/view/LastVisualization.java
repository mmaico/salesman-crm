package br.com.kproj.salesman.notifications2.logview.domain.model.view;

import br.com.kproj.salesman.infrastructure.model.ValueObject;
import br.com.kproj.salesman.notifications2.logview.domain.model.user.User;


public class LastVisualization implements ValueObject {

    private final User user;

    private final NotificationType type;


    public LastVisualization(User user, NotificationType type) {
        this.user = user;
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public NotificationType getType() {
        return type;
    }

    public static LastVisualization createLastViewing(User user, NotificationType type) {
        return new LastVisualization(user, type);
    }
}
