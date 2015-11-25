package br.com.kproj.salesman.delivery.application;


import br.com.kproj.salesman.infrastructure.entity.task.TaskTemplate;
import br.com.kproj.salesman.infrastructure.service.ModelService;

public interface TaskTemplateService extends ModelService<TaskTemplate> {

    TaskTemplate register(TaskTemplate taskTemplate);

}
