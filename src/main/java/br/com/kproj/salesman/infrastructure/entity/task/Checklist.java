package br.com.kproj.salesman.infrastructure.entity.task;

import br.com.kproj.salesman.infrastructure.entity.Identifiable;

import javax.persistence.*;

@Entity
@Table(name="checklists")
public class Checklist extends Identifiable {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @Column(name="is_done")
    private Boolean isDone;

    @ManyToOne
    @JoinColumn(name="task_id")
    private Task task;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(Boolean isDone) {
        this.isDone = isDone;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
