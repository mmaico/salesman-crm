package br.com.kproj.salesman.infrastructure.entity.task_definitions;


import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.entity.OperationRegionEntity;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name="task_definitions")
public class TaskDefinitionEntity extends Identifiable {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "tasktemplate.title.is.empty")
    private String title;
    private String description;

    @Column(name="quantity_days_to_finish_after_signed_contract")
    private Integer quantityDaysToFinish;

    @OneToMany(mappedBy = "taskDefinition")
    private List<ChecklistDefinitionEntity> checklists;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private TaskDefinitionTypeEntity type;

    @ManyToOne
    @JoinColumn(name="saleable_unit_id")
    private SaleableUnitEntity saleable;

    @ManyToOne
    @JoinColumn(name="operation_region_id")
    @NotNull(message = "tasktemplate.region.not.informed")
    private OperationRegionEntity region;

    public TaskDefinitionEntity() {}
    public TaskDefinitionEntity(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantityDaysToFinish() {
        return quantityDaysToFinish;
    }

    public void setQuantityDaysToFinish(Integer quantityDaysToFinish) {
        this.quantityDaysToFinish = quantityDaysToFinish;
    }

    public List<ChecklistDefinitionEntity> getChecklists() {
        return checklists;
    }

    public void setChecklists(List<ChecklistDefinitionEntity> checklists) {
        this.checklists = checklists;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TaskDefinitionTypeEntity getType() {
        return type;
    }

    public void setType(TaskDefinitionTypeEntity type) {
        this.type = type;
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
