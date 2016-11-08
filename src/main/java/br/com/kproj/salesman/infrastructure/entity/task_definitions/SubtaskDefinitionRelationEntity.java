package br.com.kproj.salesman.infrastructure.entity.task_definitions;


import br.com.kproj.salesman.infrastructure.entity.Identifiable;

import javax.persistence.*;

@Entity
@Table(name="subtask_definition_relations")
public class SubtaskDefinitionRelationEntity extends Identifiable {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "subtask_id")
    private SubtaskDefinitionEntity subtask;

    @ManyToOne
    @JoinColumn(name = "roottask_id")
    private RootTaskDefinitionEntity rootTask;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SubtaskDefinitionEntity getSubtask() {
        return subtask;
    }

    public void setSubtask(SubtaskDefinitionEntity subtask) {
        this.subtask = subtask;
    }

    public RootTaskDefinitionEntity getRootTask() {
        return rootTask;
    }

    public void setRootTask(RootTaskDefinitionEntity rootTask) {
        this.rootTask = rootTask;
    }
}
