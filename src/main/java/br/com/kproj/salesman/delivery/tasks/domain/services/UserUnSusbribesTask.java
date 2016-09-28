package br.com.kproj.salesman.delivery.tasks.domain.services;

import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.Task;

@FunctionalInterface
public interface UserUnSusbribesTask {

    void the(Task task);
}
