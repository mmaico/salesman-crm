package br.com.kproj.salesman.delivery.tasks.domain.model.tasks;


import br.com.kproj.salesman.delivery.tasks.application.validators.TaskIgnoreRules;

public interface TaskValidator {

    void checkRules(Task task);

    void checkRules(Task task, TaskIgnoreRules rules);
}
