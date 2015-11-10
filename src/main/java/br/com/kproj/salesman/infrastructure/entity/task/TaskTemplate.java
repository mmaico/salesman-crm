package br.com.kproj.salesman.infrastructure.entity.task;


import br.com.kproj.salesman.infrastructure.entity.AppFile;
import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnit;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="task_template")
public class TaskTemplate extends Identifiable {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String description;

    @OneToMany
    @JoinColumn(name="task_template_parent_id")
    private List<TaskTemplate> tasksTemplate;

    @Column(name="quantity_days_to_finish_after_signed_contract")
    private Integer quantityDaysTofinishAfertSignedContract;

    @OneToMany
    @JoinColumn(name="task_template_id")
    private List<AppFile> files;

    @OneToMany(mappedBy = "taskTemplate")
    private List<ChecklistTemplate> checklistTemplates;

    @OneToMany(mappedBy = "taskTemplate")
    private List<TaskCostTemplate> tasksCostsTemplate;

    @ManyToOne
    @JoinColumn(name="saleable_unit_id")
    private SaleableUnit saleable;


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

    public List<TaskTemplate> getTasksTemplate() {
        return tasksTemplate;
    }

    public void setTasksTemplate(List<TaskTemplate> tasksTemplate) {
        this.tasksTemplate = tasksTemplate;
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

    public List<ChecklistTemplate> getChecklistTemplates() {
        return checklistTemplates;
    }

    public void setChecklistTemplates(List<ChecklistTemplate> checklistTemplates) {
        this.checklistTemplates = checklistTemplates;
    }

    public List<TaskCostTemplate> getTasksCostsTemplate() {
        return tasksCostsTemplate;
    }

    public void setTasksCostsTemplate(List<TaskCostTemplate> tasksCostsTemplate) {
        this.tasksCostsTemplate = tasksCostsTemplate;
    }

    public SaleableUnit getSaleable() {
        return saleable;
    }

    public void setSaleable(SaleableUnit saleable) {
        this.saleable = saleable;
    }


    @Override
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
