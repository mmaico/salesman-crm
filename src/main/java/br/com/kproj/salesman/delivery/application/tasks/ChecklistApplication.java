package br.com.kproj.salesman.delivery.application.tasks;


import br.com.kproj.salesman.infrastructure.entity.task.Checklist;
import br.com.kproj.salesman.infrastructure.entity.task.Task;
import br.com.kproj.salesman.infrastructure.service.ModelLegacyService;

import java.util.List;

public interface ChecklistApplication extends ModelLegacyService<Checklist> {

    Checklist register(Checklist checklist);

    List<Checklist> findCheckListBy(Task task);

    void completed(Checklist checklist);

}
