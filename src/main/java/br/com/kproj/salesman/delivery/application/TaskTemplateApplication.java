package br.com.kproj.salesman.delivery.application;


import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnit;
import br.com.kproj.salesman.infrastructure.entity.task.TaskTemplate;
import br.com.kproj.salesman.infrastructure.service.ModelService;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskTemplateApplication extends ModelService<TaskTemplate> {

    TaskTemplate register(TaskTemplate taskTemplate);

    List<TaskTemplate> findTaskTemplateBy(SaleableUnit saleable);

    List<TaskTemplate> findTaskTemplateOnlyRootBy(SaleableUnit saleable);

    void remove(TaskTemplate taskTemplate);

}
