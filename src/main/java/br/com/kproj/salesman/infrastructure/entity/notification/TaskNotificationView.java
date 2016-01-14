package br.com.kproj.salesman.infrastructure.entity.notification;


import br.com.kproj.salesman.infrastructure.entity.User;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="task_notification_view")
@PrimaryKeyJoinColumn(name="notification_view_id")
public class TaskNotificationView extends NotificationView {

    /**
     *
     */
    private static final long serialVersionUID = 2728388686834419769L;


    @OneToOne
    @PrimaryKeyJoinColumn
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
