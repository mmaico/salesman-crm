package br.com.kproj.salesman.assistants.activities.domain.model.workspace;


import br.com.kproj.salesman.assistants.activities.domain.model.personal.Activities;
import br.com.kproj.salesman.infrastructure.configuration.annotations.Aggregator;
import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;

@Aggregator
public class Workspace extends ModelIdentifiable {

    private Long id;

    private Activities activities;


    public Workspace() {
    }

    public Workspace(Long id, Activities activities) {
        this.id = id;
        this.activities = activities;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Activities getActivities() {
        return activities;
    }

    public void setActivities(Activities activities) {
        this.activities = activities;
    }

    public static Workspace workspace(Long id, Activities activities) {
        return new Workspace(id, activities);
    }

    public static Workspace workspace() {
        return new Workspace();
    }
}
