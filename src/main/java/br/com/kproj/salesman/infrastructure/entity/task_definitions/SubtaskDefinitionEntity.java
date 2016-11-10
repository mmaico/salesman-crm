package br.com.kproj.salesman.infrastructure.entity.task_definitions;


import javax.persistence.*;

@Entity
@Table(name="subtask_definitions")
public class SubtaskDefinitionEntity {

    @Id
    private Long id;

    @OneToOne
    @JoinColumn(name = "task_definition_id")
    private TaskDefinitionEntity taskDefinition;

    @ManyToOne
    @JoinColumn(name="roottask_id")
    private RootTaskDefinitionEntity parent;


    public RootTaskDefinitionEntity getParent() {
        return parent;
    }

    public void setParent(RootTaskDefinitionEntity parent) {
        this.parent = parent;
    }

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
