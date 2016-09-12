package br.com.kproj.salesman.infrastructure.entity.task;


import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="checklist_templates")
public class ChecklistTemplateEntity extends Identifiable {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "checklist.template.name.invalid")
    private String name;

    @ManyToOne
    @JoinColumn(name="task_template_id")
    @NotNull(message = "checklist.template.tasktemplate.null")
    private TaskTemplateEntity taskTemplateEntity;

    public ChecklistTemplateEntity(){}
    public ChecklistTemplateEntity(Long id){
        this.id = id;
    }

    @Override
    public Long getId() {
        return this.id;
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

    public TaskTemplateEntity getTaskTemplateEntity() {
        return taskTemplateEntity;
    }

    public void setTaskTemplateEntity(TaskTemplateEntity taskTemplateEntity) {
        this.taskTemplateEntity = taskTemplateEntity;
    }
}
