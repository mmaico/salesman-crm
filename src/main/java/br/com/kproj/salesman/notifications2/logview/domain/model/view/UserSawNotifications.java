package br.com.kproj.salesman.notifications2.logview.domain.model.view;


import br.com.kproj.salesman.infrastructure.model.ValueObject;
import br.com.kproj.salesman.notifications2.logview.domain.model.user.User;

public class UserSawNotifications implements ValueObject {

    private final User user;
    private final NotificationType type;


    public UserSawNotifications(User user, NotificationType type) {
        this.user = user;
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public NotificationType getType() {
        return type;
    }
}
