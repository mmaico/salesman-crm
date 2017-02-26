package br.com.kproj.salesman.assistants.activities.domain.model.personal;


import br.com.kproj.salesman.assistants.activities.domain.model.user.Assigner;
import br.com.kproj.salesman.assistants.activities.domain.model.user.Owner;
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

	public ActivityBuilder withDeadline(Date date) {
		this.entity.setDeadline(date);
		return this;
	}

	public ActivityBuilder withOwner(Long ownerId) {
		this.entity.setOwner(new Owner(ownerId));
		return this;
	}

	public ActivityBuilder withAssigner(Long assignerId) {
		this.entity.setAssigner(new Assigner(assignerId));
		return this;
	}

	public ActivityBuilder withStatus(Status status) {
		this.entity.setStatus(status);
		return this;
	}


	public static ActivityBuilder createActivity(Long id) {
		return new ActivityBuilder(id);
	}

	public static ActivityBuilder createActivity() {
		return new ActivityBuilder();
	}
}
