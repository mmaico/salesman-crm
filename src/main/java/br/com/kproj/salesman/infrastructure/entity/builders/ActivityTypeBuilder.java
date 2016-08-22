package br.com.kproj.salesman.infrastructure.entity.builders;

import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.ActivityType;


public class ActivityTypeBuilder extends AbstractBuilder<ActivityType> {

	private ActivityType activityType = new ActivityType();

    public ActivityTypeBuilder() {}

    public ActivityTypeBuilder(Long id) {
        activityType.setId(id);
    }

    public ActivityTypeBuilder(String name) {
        activityType.setName(name);
    }

    public static ActivityTypeBuilder create(String name) {
        return new ActivityTypeBuilder(name);
    }


	public ActivityType build() {
		return this.activityType;
	}


	
}
