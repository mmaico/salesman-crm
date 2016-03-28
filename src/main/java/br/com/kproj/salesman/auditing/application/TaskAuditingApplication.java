package br.com.kproj.salesman.auditing.application;

import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.auditing.TaskAudinting;
import br.com.kproj.salesman.infrastructure.entity.task.Task;
import br.com.kproj.salesman.infrastructure.service.ModelService;

import java.util.Optional;


public interface TaskAuditingApplication extends ModelService<TaskAudinting> {

    Optional<TaskAudinting> registerAuditing(Task task, User userThatChanged);
}
