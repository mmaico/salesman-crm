package br.com.kproj.salesman.infrastructure.entity.task;


import br.com.kproj.salesman.infrastructure.entity.AppFile;
import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.entity.OperationRegionEntity;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import com.google.common.collect.Lists;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name="task_templates")
public class TaskTemplateEntity extends Identifiable {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "tasktemplate.title.is.empty")
    private String title;
    private String description;

    @OneToMany
    @JoinColumn(name="task_template_parent_id")
    @Cascade(CascadeType.DELETE)
    private List<TaskTemplateEntity> templatesChilds;


    @Column(name = "task_template_parent_id", updatable =false, insertable = false)
    private Long parentId;

    @Column(name="quantity_days_to_finish_after_signed_contract")
    private Integer quantityDaysTofinishAfertSignedContract;

    @OneToMany
    @JoinColumn(name="task_template_id")
    private List<AppFile> files;

    @OneToMany(mappedBy = "taskTemplate")
    @Cascade(CascadeType.DELETE)
    private List<ChecklistTemplateEntity> checklistTemplateEntities;

    @OneToMany(mappedBy = "taskTemplate")
    @Cascade(CascadeType.DELETE)
    private List<TaskCostTemplate> tasksCostsTemplates;

    @ManyToOne
    @JoinColumn(name="saleable_unit_id")
    private SaleableUnitEntity saleable;

    @ManyToOne
    @JoinColumn(name="operation_region_id")
    @NotNull(message = "tasktemplate.region.not.informed")
    private OperationRegionEntity region;

    @Transient
    private TaskTemplateEntity parent;

    public TaskTemplateEntity() {}
    public TaskTemplateEntity(Long id) {
        this.id = id;
    }


    public void addChild(TaskTemplateEntity taskTemplateEntity) {
        if(this.templatesChilds == null) {
            this.templatesChilds = Lists.newArrayList();
        }
        this.templatesChilds.add(taskTemplateEntity);
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

    public List<TaskTemplateEntity> getTemplatesChilds() {
        return templatesChilds;
    }

    public void setTemplatesChilds(List<TaskTemplateEntity> templatesChilds) {
        this.templatesChilds = templatesChilds;
    }

    public Integer getQuantityDaysTofinishAfertSignedContract() {
        return quantityDaysTofinishAfertSignedContract;
    }

    public void setQuantityDaysTofinishAfertSignedContract(Integer quantityDaysTofinishAfertSignedContract) {
        this.quantityDaysTofinishAfertSignedContract = quantityDaysTofinishAfertSignedContract;
    }

    public List<AppFile> getFiles() {
        return files;
    }

    public void setFiles(List<AppFile> files) {
        this.files = files;
    }

    public List<ChecklistTemplateEntity> getChecklistTemplateEntities() {
        return checklistTemplateEntities;
    }

    public void setChecklistTemplateEntities(List<ChecklistTemplateEntity> checklistTemplateEntities) {
        this.checklistTemplateEntities = checklistTemplateEntities;
    }

    public List<TaskCostTemplate> getTasksCostsTemplates() {
        return tasksCostsTemplates;
    }

    public void setTasksCostsTemplates(List<TaskCostTemplate> tasksCostsTemplates) {
        this.tasksCostsTemplates = tasksCostsTemplates;
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


    @Override
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TaskTemplateEntity getParent() {
        return parent;
    }

    public void setParent(TaskTemplateEntity parent) {
        this.parent = parent;
    }

    public Boolean hasValidParent() {
        return parent != null && !parent.isNew();

    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}
