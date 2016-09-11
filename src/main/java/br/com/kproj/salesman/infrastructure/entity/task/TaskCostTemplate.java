package br.com.kproj.salesman.infrastructure.entity.task;


import br.com.kproj.salesman.infrastructure.entity.Identifiable;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="task_cost_templates")
public class TaskCostTemplate extends Identifiable {


    @Id
    @GeneratedValue
    private Long id;

    private BigDecimal cost;

    @Column(name="is_internal")
    private Boolean isInternal;

    @ManyToOne
    @JoinColumn(name="task_template_id")
    private TaskTemplateEntity taskTemplateEntity;

    @Override
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

    public TaskTemplateEntity getTaskTemplateEntity() {
        return taskTemplateEntity;
    }

    public void setTaskTemplateEntity(TaskTemplateEntity taskTemplateEntity) {
        this.taskTemplateEntity = taskTemplateEntity;
    }
}
