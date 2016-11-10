package br.com.kproj.salesman.infrastructure.entity.task_definitions;


import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.entity.task.ChecklistTemplateEntity;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="task_definitions")
@Inheritance(strategy = InheritanceType.JOINED)
public class TaskDefinitionEntity extends Identifiable {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "tasktemplate.title.is.empty")
    private String title;
    private String description;

    @Column(name="quantity_days_to_finish_after_signed_contract")
    private Integer quantityDaysTofinishAfertSignedContract;

    @OneToMany(mappedBy = "taskDefinition")
    @Cascade(CascadeType.DELETE)
    private List<ChecklistDefinitionEntity> checklist;

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

    public Integer getQuantityDaysTofinishAfertSignedContract() {
        return quantityDaysTofinishAfertSignedContract;
    }

    public void setQuantityDaysTofinishAfertSignedContract(Integer quantityDaysTofinishAfertSignedContract) {
        this.quantityDaysTofinishAfertSignedContract = quantityDaysTofinishAfertSignedContract;
    }

    public List<ChecklistDefinitionEntity> getChecklist() {
        return checklist;
    }

    public void setChecklist(List<ChecklistDefinitionEntity> checklist) {
        this.checklist = checklist;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
