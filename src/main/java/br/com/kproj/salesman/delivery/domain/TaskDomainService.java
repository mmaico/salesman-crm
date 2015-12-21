package br.com.kproj.salesman.delivery.domain;


import br.com.kproj.salesman.infrastructure.entity.task.Task;
import br.com.kproj.salesman.infrastructure.service.DomainBusinessRules;

public interface TaskDomainService extends DomainBusinessRules<Task> {


    void prepareToSave(Task task);
}
