package br.com.kproj.salesman.timelines.timeline.domain.model.timeline;


import br.com.kproj.salesman.infrastructure.entity.builders.AbstractBuilder;
import br.com.kproj.salesman.timelines.timeline.domain.model.activities.Activity;

import java.util.List;

public class TimelineBuilder extends AbstractBuilder<Timeline>  {

	public TimelineBuilder() {
		this.entity = new Timeline();
	}

	public TimelineBuilder(Long id) {
		this();
		this.entity.setId(id);
	}

	public TimelineBuilder withActivity(Long activityId) {
		this.entity.getActivities().add(new Activity(activityId));
		return this;
	}

	public TimelineBuilder withActivities(List<Activity> activities) {
		this.entity.setActivities(activities);
		return this;
	}

	public static TimelineBuilder createTimeline(Long id) {
		return new TimelineBuilder(id);
	}

	public static TimelineBuilder createTimeline() {
		return new TimelineBuilder();
	}
}
