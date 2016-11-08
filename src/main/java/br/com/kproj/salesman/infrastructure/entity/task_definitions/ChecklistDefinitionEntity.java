package br.com.kproj.salesman.infrastructure.entity.task_definitions;


import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.entity.task.TaskTemplateEntity;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="checklist_definitions")
public class ChecklistDefinitionEntity extends Identifiable {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "checklist.template.name.invalid")
    private String name;

    @ManyToOne
    @JoinColumn(name="task_definition_id")
    @NotNull(message = "checklist.definition.taskdefinition.null")
    private TaskDefinitionEntity taskDefinition;

    public ChecklistDefinitionEntity(){}
    public ChecklistDefinitionEntity(Long id){
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

    public TaskDefinitionEntity getTaskDefinition() {
        return taskDefinition;
    }

    public void setTaskDefinition(TaskDefinitionEntity taskDefinition) {
        this.taskDefinition = taskDefinition;
    }
}
