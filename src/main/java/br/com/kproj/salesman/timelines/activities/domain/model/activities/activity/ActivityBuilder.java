package br.com.kproj.salesman.timelines.activities.domain.model.activities.activity;


import br.com.kproj.salesman.infrastructure.entity.builders.AbstractBuilder;
import br.com.kproj.salesman.timelines.activities.domain.model.timeline.Timeline;
import br.com.kproj.salesman.timelines.activities.domain.model.user.User;

import java.util.Date;


public class ActivityBuilder extends AbstractBuilder<Activity>  {

	public ActivityBuilder() {
		this.entity = new Activity();
	}

	public ActivityBuilder(Long id) {
		this();
		this.entity.setId(id);
	}

	public ActivityBuilder withDescription(String description) {
		this.entity.setDescription(description);
		return this;
	}

	public ActivityBuilder withCreation(Date date) {
		this.entity.setCreation(date);
		return this;
	}

	public ActivityBuilder withUser(User user) {
		this.entity.setUser(user);
		return this;
	}

	public ActivityBuilder withTimeline(Timeline timeline) {
		this.entity.setTimeline(timeline);
		return this;
	}

	public ActivityBuilder withTag(Tag tag) {
		this.entity.setTag(tag);
		return this;
	}

	public ActivityBuilder withTag(String tag) {
		this.entity.setTag(Tag.valueOf(tag));
		return this;
	}



	public static ActivityBuilder createActivity(Long id) {
		return new ActivityBuilder(id);
	}

	public static ActivityBuilder createActivity() {
		return new ActivityBuilder();
	}
}
