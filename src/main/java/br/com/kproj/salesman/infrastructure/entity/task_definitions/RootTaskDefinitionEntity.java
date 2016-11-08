package br.com.kproj.salesman.infrastructure.entity.task_definitions;


import br.com.kproj.salesman.infrastructure.entity.OperationRegionEntity;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name="roottask_definitions")
public class RootTaskDefinitionEntity extends TaskDefinitionEntity {

    @ManyToOne
    @JoinColumn(name="saleable_unit_id")
    private SaleableUnitEntity saleable;

    @ManyToOne
    @JoinColumn(name="operation_region_id")
    @NotNull(message = "tasktemplate.region.not.informed")
    private OperationRegionEntity region;


    @OneToMany(fetch= FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "rootTask")
    private List<SubtaskDefinitionRelationEntity> relations;


    public List<SubtaskDefinitionRelationEntity> getRelations() {
        return relations;
    }

    public void setRelations(List<SubtaskDefinitionRelationEntity> relations) {
        this.relations = relations;
    }

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
}
