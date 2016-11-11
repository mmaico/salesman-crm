package br.com.kproj.salesman.infrastructure.entity.task_definitions;


import br.com.kproj.salesman.infrastructure.entity.Identifiable;

import javax.persistence.*;

@Entity
@Table(name="roottask_definitions")
public class RootTaskDefinitionEntity extends Identifiable {

    @Id
    private Long id;

    @OneToOne
    @JoinColumn(name = "task_definition_id")
    private TaskDefinitionEntity taskDefinition;

    public RootTaskDefinitionEntity(Long id) {
        this.id = id;
    }

    public RootTaskDefinitionEntity() {}

    @Override
    public Long getId() {
        return id;
    }

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
