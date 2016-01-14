package br.com.kproj.salesman.infrastructure.entity.notification;


import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.entity.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="notifications_log_view")
public class UserNotificationLogView extends Identifiable {

    /**
     *
     */
    private static final long serialVersionUID = 2728388686834419769L;

    public enum TypeLogView{
        PROPOSAL_NOTIFICATION, TASK_NOTIFICATION
    }

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @PrimaryKeyJoinColumn
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="last_view")
    private Date lastVisualization;

    @Column(name="type_log_view")
    private TypeLogView typeLogView;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getLastVisualization() {
        return lastVisualization;
    }

    public void setLastVisualization(Date lastVisualization) {
        this.lastVisualization = lastVisualization;
    }

    public TypeLogView getTypeLogView() {
        return typeLogView;
    }

    public void setTypeLogView(TypeLogView typeLogView) {
        this.typeLogView = typeLogView;
    }
}
