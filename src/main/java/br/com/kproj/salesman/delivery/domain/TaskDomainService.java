package br.com.kproj.salesman.delivery.domain;


import br.com.kproj.salesman.infrastructure.entity.task.TaskEntity;
import br.com.kproj.salesman.infrastructure.service.DomainBusinessRulesLegacy;

public interface TaskDomainService extends DomainBusinessRulesLegacy<TaskEntity> {


    void prepareToSave(TaskEntity taskEntity);
}
