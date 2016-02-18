package br.com.kproj.salesman.assistants.activities.application;


import br.com.kproj.salesman.infrastructure.entity.activities.ActivityChecklist;
import br.com.kproj.salesman.infrastructure.entity.activities.PersonalActivity;
import br.com.kproj.salesman.infrastructure.entity.task.Checklist;
import br.com.kproj.salesman.infrastructure.entity.task.Task;
import br.com.kproj.salesman.infrastructure.service.ModelService;

import java.util.List;

public interface ActivityChecklistApplication extends ModelService<ActivityChecklist> {

    ActivityChecklist register(ActivityChecklist checklist);

    List<ActivityChecklist> findCheckListBy(PersonalActivity personalActivity);

    void completed(ActivityChecklist activityChecklist);

}
