package br.com.kproj.salesman.assistants.activities.application;


import br.com.kproj.salesman.infrastructure.entity.activities.ActivityChecklistEntity;
import br.com.kproj.salesman.infrastructure.entity.activities.PersonalActivityEntity;
import br.com.kproj.salesman.infrastructure.service.ModelLegacyService;

import java.util.List;

public interface ActivityChecklistApplication extends ModelLegacyService<ActivityChecklistEntity> {

    ActivityChecklistEntity register(ActivityChecklistEntity checklist);

    List<ActivityChecklistEntity> findCheckListBy(PersonalActivityEntity personalActivityEntity);

    void completed(ActivityChecklistEntity activityChecklistEntity);

}
