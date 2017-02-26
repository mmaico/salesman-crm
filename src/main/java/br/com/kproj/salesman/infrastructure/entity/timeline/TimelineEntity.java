package br.com.kproj.salesman.infrastructure.entity.timeline;


import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.entity.timeline.items.TimelineActivity;
import com.google.common.collect.Lists;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="timelines")
public class TimelineEntity extends Identifiable {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "timeline")
    private List<TimelineActivity> activities = Lists.newArrayList();


    public TimelineEntity() {}

    public TimelineEntity(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<TimelineActivity> getActivities() {
        return activities;
    }

    public void setActivities(List<TimelineActivity> activities) {
        this.activities = activities;
    }

    public void addActivity(TimelineActivity activity) {
        if (this.activities == null) {
            this.activities = Lists.newArrayList();
        }

        this.activities.add(activity);
    }

}
