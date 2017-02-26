package br.com.kproj.salesman.infrastructure.entity.timeline.items.relationship;

import br.com.kproj.salesman.infrastructure.entity.AppFileEntity;
import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.entity.timeline.items.TimelineActivity;

import javax.persistence.*;

@Entity
@Table(name="activity_media_relationship")
public class ActivityMediaRelationshipEntity extends Identifiable {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "media_id")
    private AppFileEntity media;

    @ManyToOne
    @JoinColumn(name = "activity_id")
    private TimelineActivity activity;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AppFileEntity getMedia() {
        return media;
    }

    public void setMedia(AppFileEntity media) {
        this.media = media;
    }

    public TimelineActivity getActivity() {
        return activity;
    }

    public void setActivity(TimelineActivity activity) {
        this.activity = activity;
    }
}
