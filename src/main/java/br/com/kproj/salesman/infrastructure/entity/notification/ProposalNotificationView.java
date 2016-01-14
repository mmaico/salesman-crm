package br.com.kproj.salesman.infrastructure.entity.notification;


import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.entity.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="proposal_notification_view")
@PrimaryKeyJoinColumn(name="notification_view_id")
public class ProposalNotificationView extends NotificationView {

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
