package br.com.kproj.salesman.timelines.timeline.domain.model.timeline;

import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import br.com.kproj.salesman.timelines.timeline.domain.model.activities.Activity;
import com.github.mmaico.shared.annotations.Model;
import com.google.common.collect.Lists;


import java.util.List;

@Model
public class Timeline extends ModelIdentifiable {

    private Long id;
    private List<Activity> activities = Lists.newArrayList();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }
}
