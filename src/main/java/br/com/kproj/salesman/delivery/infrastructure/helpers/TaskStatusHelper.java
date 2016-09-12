package br.com.kproj.salesman.delivery.infrastructure.helpers;


import br.com.kproj.salesman.infrastructure.entity.enums.TaskStatusEntity;


public class TaskStatusHelper {

    public TaskStatusEntity waiting() {
        return TaskStatusEntity.WAITING;
    }

    public TaskStatusEntity done() {
        return TaskStatusEntity.DONE;
    }

    public TaskStatusEntity problem() {
        return TaskStatusEntity.PROBLEM;
    }

    public TaskStatusEntity started() {
        return TaskStatusEntity.STATED;
    }
}
