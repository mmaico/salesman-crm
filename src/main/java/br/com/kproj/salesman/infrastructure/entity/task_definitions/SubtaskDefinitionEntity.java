package br.com.kproj.salesman.infrastructure.entity.task_definitions;


import javax.persistence.*;

@Entity
@Table(name="subtask_definitions")
public class SubtaskDefinitionEntity extends TaskDefinitionEntity {


    @ManyToOne
    @JoinColumn(name="roottask_id")
    private RootTaskDefinitionEntity parent;


    public RootTaskDefinitionEntity getParent() {
        return parent;
    }

    public void setParent(RootTaskDefinitionEntity parent) {
        this.parent = parent;
    }
}
