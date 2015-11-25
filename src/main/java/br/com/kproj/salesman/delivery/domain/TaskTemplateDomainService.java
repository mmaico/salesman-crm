package br.com.kproj.salesman.delivery.domain;


import br.com.kproj.salesman.infrastructure.entity.task.TaskTemplate;

public interface TaskTemplateDomainService {

    void checkBusinessRulesFor(TaskTemplate taskTemplate);
}
