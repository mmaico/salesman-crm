package br.com.kproj.salesman.delivery.infrastructure.helpers;


import br.com.kproj.salesman.infrastructure.entity.enums.TaskStatus;


public class TaskStatusHelper {

    public TaskStatus waiting() {
        return TaskStatus.WAITING;
    }

    public TaskStatus done() {
        return TaskStatus.DONE;
    }

    public TaskStatus problem() {
        return TaskStatus.PROBLEM;
    }

    public TaskStatus started() {
        return TaskStatus.STATED;
    }
}
