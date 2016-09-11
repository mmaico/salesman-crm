package br.com.kproj.salesman.delivery.application.tasktemplates;


import br.com.kproj.salesman.infrastructure.entity.task.ChecklistTemplateEntity;
import br.com.kproj.salesman.infrastructure.entity.task.TaskTemplateEntity;
import br.com.kproj.salesman.infrastructure.service.ModelLegacyService;

import java.util.List;

public interface ChecklistTemplateApplication extends ModelLegacyService<ChecklistTemplateEntity> {

    ChecklistTemplateEntity register(ChecklistTemplateEntity checklistTemplateEntity);

    List<ChecklistTemplateEntity> findCheckListBy(TaskTemplateEntity taskTemplateEntity);

    void delete(ChecklistTemplateEntity checklistTemplateEntity);

}
