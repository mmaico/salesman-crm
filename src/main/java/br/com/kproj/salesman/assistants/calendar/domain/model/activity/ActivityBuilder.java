package br.com.kproj.salesman.assistants.calendar.domain.model.activity;


import br.com.kproj.salesman.infrastructure.entity.builders.AbstractBuilder;

import java.util.Date;

public class ActivityBuilder extends AbstractBuilder<Activity>  {

	public ActivityBuilder() {
		this.entity = new Activity();
	}

	public ActivityBuilder(Long id) {
		this();
		this.entity.setId(id);
	}

	public ActivityBuilder withTitle(String title) {
		this.entity.setTitle(title);
		return this;
	}

	public ActivityBuilder withDescription(String description) {
		this.entity.setDescription(description);
		return this;
	}

	public ActivityBuilder withLocation(String location) {
		this.entity.setLocation(location);
		return this;
	}

	public ActivityBuilder withStart(Date start) {
		this.entity.setStart(start);
		return this;
	}

	public ActivityBuilder withEnd(Date end) {
		this.entity.setEnd(end);
		return this;
	}

	public ActivityBuilder withAllDay(Boolean  allDay) {
		this.entity.setAllDay(allDay);
		return this;
	}

	public static ActivityBuilder createActivity(Long id) {
		return new ActivityBuilder(id);
	}

	public static ActivityBuilder createActivity() {
		return new ActivityBuilder();
	}

}
