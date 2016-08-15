package br.com.kproj.salesman.delivery.application.tasktemplates;


import br.com.kproj.salesman.infrastructure.entity.task.ChecklistTemplate;
import br.com.kproj.salesman.infrastructure.entity.task.TaskTemplate;
import br.com.kproj.salesman.infrastructure.service.ModelLegacyService;

import java.util.List;

public interface ChecklistTemplateApplication extends ModelLegacyService<ChecklistTemplate> {

    ChecklistTemplate register(ChecklistTemplate checklistTemplate);

    List<ChecklistTemplate> findCheckListBy(TaskTemplate taskTemplate);

    void delete(ChecklistTemplate checklistTemplate);

}
