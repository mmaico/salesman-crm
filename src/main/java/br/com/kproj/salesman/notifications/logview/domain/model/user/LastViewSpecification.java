package br.com.kproj.salesman.notifications.logview.domain.model.user;


import br.com.kproj.salesman.notifications.logview.domain.model.view.NotificationType;

public class LastViewSpecification {

    private User user;
    private NotificationType type;

    public static LastViewSpecification which() {
        return new LastViewSpecification();
    }

    public LastViewSpecification lastTimeThat(User user) {
        this.user = user;
        return this;
    }

    public LastViewSpecification viewedNotifications() {
        return this;
    }

    public LastViewSpecification of(NotificationType type) {
        this.type = type;
        return this;
    }

    public User getUser() {
        return user;
    }

    public NotificationType getType() {
        return type;
    }
}
