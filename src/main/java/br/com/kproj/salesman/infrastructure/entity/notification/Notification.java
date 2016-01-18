package br.com.kproj.salesman.infrastructure.entity.notification;

import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.helpers.files.annotations.Media;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name="notifications")
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="descriminator", discriminatorType=DiscriminatorType.STRING)
public class Notification extends Identifiable {

    @Id
    @GeneratedValue
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_date")
    private Date createDate;

    @ManyToOne
    @JoinColumn(name="user_notified_id")
    private User notified;


    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public User getNotified() {
        return notified;
    }

    public void setNotified(User notified) {
        this.notified = notified;
    }
}
