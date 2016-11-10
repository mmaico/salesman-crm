package br.com.kproj.salesman.infrastructure.entity.task_definitions;


import br.com.kproj.salesman.infrastructure.entity.OperationRegionEntity;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="roottask_definitions")
public class RootTaskDefinitionEntity extends TaskDefinitionEntity {

    @Id
    private Long id;

    @OneToOne
    @JoinColumn(name = "task_definition_id")
    private TaskDefinitionEntity taskDefinition;

    @ManyToOne
    @JoinColumn(name="saleable_unit_id")
    private SaleableUnitEntity saleable;

    @ManyToOne
    @JoinColumn(name="operation_region_id")
    @NotNull(message = "tasktemplate.region.not.informed")
    private OperationRegionEntity region;

    public RootTaskDefinitionEntity(Long id) {
        super.setId(id);
    }

    public RootTaskDefinitionEntity() {}

    public SaleableUnitEntity getSaleable() {
        return saleable;
    }

    public void setSaleable(SaleableUnitEntity saleable) {
        this.saleable = saleable;
    }

    public OperationRegionEntity getRegion() {
        return region;
    }

    public void setRegion(OperationRegionEntity region) {
        this.region = region;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public TaskDefinitionEntity getTaskDefinition() {
        return taskDefinition;
    }

    public void setTaskDefinition(TaskDefinitionEntity taskDefinition) {
        this.taskDefinition = taskDefinition;
    }
}
