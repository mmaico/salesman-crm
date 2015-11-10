package br.com.kproj.salesman.infrastructure.entity.task;

import br.com.kproj.salesman.infrastructure.entity.Identifiable;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="task_costs")
public class TaskCost extends Identifiable {

    @Id
    @GeneratedValue
    private Long id;

    private BigDecimal cost;

    @Column(name="is_internal")
    private Boolean isInternal;

    private String description;

    @ManyToOne
    @JoinColumn(name="task_id")
    private Task task;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Boolean getIsInternal() {
        return isInternal;
    }

    public void setIsInternal(Boolean isInternal) {
        this.isInternal = isInternal;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
