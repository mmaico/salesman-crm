package br.com.kproj.salesman.infrastructure.entity.task;

import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.entity.enums.TaskStatusEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name="task_change_status_history")
public class TaskChangeHistory extends Identifiable {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name="task_id")
    @NotNull(message = "checklist.task.null")
    private TaskEntity taskEntityChanged;

    @Enumerated(EnumType.STRING)
    @Column(name="status_changed")
    private TaskStatusEntity statusChanged;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfChange;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TaskEntity getTaskEntityChanged() {
        return taskEntityChanged;
    }

    public void setTaskEntityChanged(TaskEntity taskEntityChanged) {
        this.taskEntityChanged = taskEntityChanged;
    }

    public TaskStatusEntity getStatusChanged() {
        return statusChanged;
    }

    public void setStatusChanged(TaskStatusEntity statusChanged) {
        this.statusChanged = statusChanged;
    }

    public Date getDateOfChange() {
        return dateOfChange;
    }

    public void setDateOfChange(Date dateOfChange) {
        this.dateOfChange = dateOfChange;
    }
}
