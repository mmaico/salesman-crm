package br.com.kproj.salesman.delivery.infrastructure.helpers;


import br.com.kproj.salesman.delivery.application.tasktemplates.TaskTemplateApplication;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.infrastructure.entity.task.TaskTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskTemplateHelpers {

    @Autowired
    private TaskTemplateApplication application;


    public List<TaskTemplate> findTasks(SaleableUnitEntity saleable) {
        return application.findTaskTemplateOnlyRootBy(saleable);
    }

}
