package br.com.kproj.salesman.delivery.domain;


import br.com.kproj.salesman.infrastructure.entity.task.Task;

public interface TaskDomainService {

    void checkBusinessRulesFor(Task task);
    void prepareToSave(Task task);
}
