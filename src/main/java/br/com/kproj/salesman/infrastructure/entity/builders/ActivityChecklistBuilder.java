package br.com.kproj.salesman.infrastructure.entity.builders;

import br.com.kproj.salesman.infrastructure.entity.activities.ActivityChecklistEntity;


public class ActivityChecklistBuilder extends AbstractBuilder<ActivityChecklistEntity> {

	private ActivityChecklistEntity activityChecklistEntity = new ActivityChecklistEntity();

    public ActivityChecklistBuilder() {}

    public ActivityChecklistBuilder(Long id) {
        activityChecklistEntity.setId(id);
    }



    public static ActivityChecklistBuilder createActivityChecklist(Long id) {
        return new ActivityChecklistBuilder(id);
    }


	public ActivityChecklistEntity build() {
		return this.activityChecklistEntity;
	}


	
}
