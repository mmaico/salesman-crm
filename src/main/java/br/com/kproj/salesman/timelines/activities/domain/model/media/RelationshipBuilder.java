package br.com.kproj.salesman.timelines.activities.domain.model.media;


import br.com.kproj.salesman.infrastructure.entity.builders.AbstractBuilder;
import br.com.kproj.salesman.timelines.activities.domain.model.activities.activity.Activity;


public class RelationshipBuilder extends AbstractBuilder<MediaRelationship>  {

	public RelationshipBuilder() {
		this.entity = new MediaRelationship();
	}

	public RelationshipBuilder(Long id) {
		this();
		this.entity.setId(id);
	}

	public RelationshipBuilder withMedia(Long mediaId) {
		if (mediaId != null) {
			this.entity.setMedia(new Media(mediaId));
		}
		return this;
	}

	public RelationshipBuilder withActivity(Long activityId) {
		if (activityId != null) {
			this.entity.setActivity(new Activity(activityId));
		}
		return this;
	}


	public static RelationshipBuilder createRelationship(Long id) {
		return new RelationshipBuilder(id);
	}

	public static RelationshipBuilder createRelationship() {
		return new RelationshipBuilder();
	}
}
