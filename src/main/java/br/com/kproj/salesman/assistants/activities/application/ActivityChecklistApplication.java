package br.com.kproj.salesman.assistants.activities.application;


import br.com.kproj.salesman.infrastructure.entity.activities.ActivityChecklist;
import br.com.kproj.salesman.infrastructure.entity.activities.PersonalActivity;
import br.com.kproj.salesman.infrastructure.service.ModelLegacyService;

import java.util.List;

public interface ActivityChecklistApplication extends ModelLegacyService<ActivityChecklist> {

    ActivityChecklist register(ActivityChecklist checklist);

    List<ActivityChecklist> findCheckListBy(PersonalActivity personalActivity);

    void completed(ActivityChecklist activityChecklist);

}
