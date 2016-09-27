package br.com.kproj.salesman.assistants.activities.domain.model.personal;


import br.com.kproj.salesman.infrastructure.model.CollectionBehavior;

import java.util.Collection;

public class Activities extends CollectionBehavior<Activity> {

    private final Collection<Activity> activities;

    public Activities(Collection<Activity> activities) {
        this.activities = activities;
    }

    @Override
    public Collection<Activity> getCollection() {
        return activities;
    }

    public static Activities createActivities(Collection<Activity> activities) {
        return new Activities(activities);
    }
}
