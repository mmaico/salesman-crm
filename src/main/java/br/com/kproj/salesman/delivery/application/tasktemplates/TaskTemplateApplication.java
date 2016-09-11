package br.com.kproj.salesman.delivery.application.tasktemplates;


import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.infrastructure.entity.task.TaskTemplateEntity;
import br.com.kproj.salesman.infrastructure.service.ModelLegacyService;

import java.util.List;

public interface TaskTemplateApplication extends ModelLegacyService<TaskTemplateEntity> {

    TaskTemplateEntity register(TaskTemplateEntity taskTemplateEntity);

    List<TaskTemplateEntity> findTaskTemplateBy(SaleableUnitEntity saleable);

    List<TaskTemplateEntity> findTaskTemplateOnlyRootBy(SaleableUnitEntity saleable);

    void remove(TaskTemplateEntity taskTemplateEntity);

}
