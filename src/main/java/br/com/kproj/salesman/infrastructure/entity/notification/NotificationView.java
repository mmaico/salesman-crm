package br.com.kproj.salesman.infrastructure.entity.notification;


import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.entity.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="notifications_view")
@Inheritance(strategy=InheritanceType.JOINED)
public class NotificationView extends Identifiable {

    /**
     *
     */
    private static final long serialVersionUID = 2728388686834419769L;

    @Id
    @GeneratedValue
    private Long id;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="last_view")
    private Date lastVisualization;

    public Date getLastVisualization() {
        return lastVisualization;
    }

    public void setLastVisualization(Date lastVisualization) {
        this.lastVisualization = lastVisualization;
    }

    public abstract void setUser(User userView);
    public abstract User getUser();
}
