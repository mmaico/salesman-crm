package br.com.kproj.salesman.delivery.domain;


import br.com.kproj.salesman.infrastructure.entity.task.Task;
import br.com.kproj.salesman.infrastructure.service.DomainBusinessRulesLegacy;

public interface TaskDomainService extends DomainBusinessRulesLegacy<Task> {


    void prepareToSave(Task task);
}
