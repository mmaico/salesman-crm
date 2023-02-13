package br.com.kproj.salesman.timelines.activities.domain.model.activities.activity;

import br.com.kproj.salesman.infrastructure.helpers.AutowireHelper;
import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import br.com.kproj.salesman.timelines.activities.domain.model.media.MediaRelationship;
import br.com.kproj.salesman.timelines.activities.domain.model.timeline.Timeline;
import br.com.kproj.salesman.timelines.activities.domain.model.user.User;
import com.github.mmaico.shared.annotations.Model;
import com.google.common.collect.Lists;


import java.util.Date;
import java.util.List;

@Model
public class Activity extends ModelIdentifiable {

    private Long id;

    private String description;
    private Date creation;
    private User user;
    private Timeline timeline;
    private Tag tag;
    private List<MediaRelationship> medias = Lists.newArrayList();

    public Activity() {
        AutowireHelper.autowire(this);
    }

    public Activity(Long id) {
        this();
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public Timeline getTimeline() {
        return timeline;
    }

    public void setTimeline(Timeline timeline) {
        this.timeline = timeline;
    }

    public List<MediaRelationship> getMedias() {
        return medias;
    }

    public void setMedias(List<MediaRelationship> medias) {
        this.medias = medias;
    }
}
