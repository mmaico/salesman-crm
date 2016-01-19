package br.com.kproj.salesman.infrastructure.entity.notification;

import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.entity.task.Task;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name="schedule_trigger_notification")
public class ScheduleTriggerNotification extends Identifiable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name="trigger_date")
    private Date triggerDate;

    @ManyToOne
    @JoinColumn(name="task_id")
    private Task task;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTriggerDate() {
        return triggerDate;
    }

    public void setTriggerDate(Date triggerDate) {
        this.triggerDate = triggerDate;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }


}
