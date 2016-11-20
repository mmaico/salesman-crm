package br.com.kproj.salesman.infrastructure.entity.task;


import br.com.kproj.salesman.infrastructure.entity.Identifiable;

import javax.persistence.*;

@Entity
@Table(name="subtask_delivery")
public class SubtaskEntity extends Identifiable {

    @Id
    private Long id;

    @OneToOne
    @JoinColumn(name = "task_id")
    private TaskEntity task;

    @ManyToOne
    @JoinColumn(name="roottask_id")
    private RootTaskEntity parent;

    public SubtaskEntity(Long id) {
        this.id = id;
    }
    public SubtaskEntity() {}


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TaskEntity getTask() {
        return task;
    }

    public void setTask(TaskEntity task) {
        this.task = task;
    }

    public RootTaskEntity getParent() {
        return parent;
    }

    public void setParent(RootTaskEntity parent) {
        this.parent = parent;
    }
}
