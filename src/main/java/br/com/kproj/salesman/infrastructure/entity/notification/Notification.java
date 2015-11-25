package br.com.kproj.salesman.infrastructure.entity.notification;

import br.com.kproj.salesman.infrastructure.entity.Identifiable;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "notifications")
public class Notification extends Identifiable {

    @Id
    @GeneratedValue
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date deadline;


    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }
}
