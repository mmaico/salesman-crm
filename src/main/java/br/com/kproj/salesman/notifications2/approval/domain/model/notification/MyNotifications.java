package br.com.kproj.salesman.notifications2.approval.domain.model.notification;


import br.com.kproj.salesman.infrastructure.model.ValueObject;
import br.com.kproj.salesman.notifications2.approval.domain.model.user.User;

public class MyNotifications implements ValueObject {

    private final User user;

    public MyNotifications(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public static MyNotifications create(User user) {
        return new MyNotifications(user);
    }
}
