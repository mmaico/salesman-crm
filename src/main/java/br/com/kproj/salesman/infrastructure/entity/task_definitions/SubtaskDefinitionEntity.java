package br.com.kproj.salesman.infrastructure.entity.task_definitions;


import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="subtask_definitions")
public class SubtaskDefinitionEntity extends TaskDefinitionEntity {


    @ManyToOne
    @JoinColumn(name="roottask_id")
    private RootTaskDefinitionEntity rootTask;


    public RootTaskDefinitionEntity getRootTask() {
        return rootTask;
    }

    public void setRootTask(RootTaskDefinitionEntity rootTask) {
        this.rootTask = rootTask;
    }
}
