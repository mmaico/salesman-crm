package br.com.kproj.salesman.delivery.application.tasks;


import br.com.kproj.salesman.infrastructure.entity.task.ChecklistEntity;
import br.com.kproj.salesman.infrastructure.entity.task.TaskEntity;
import br.com.kproj.salesman.infrastructure.service.ModelLegacyService;

import java.util.List;

public interface ChecklistApplication extends ModelLegacyService<ChecklistEntity> {

    ChecklistEntity register(ChecklistEntity checklistEntity);

    List<ChecklistEntity> findCheckListBy(TaskEntity taskEntity);

    void completed(ChecklistEntity checklistEntity);

}
