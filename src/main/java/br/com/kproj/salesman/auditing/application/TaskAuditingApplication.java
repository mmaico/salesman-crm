package br.com.kproj.salesman.auditing.application;

import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.auditing.BusinessProposalAudinting;
import br.com.kproj.salesman.infrastructure.entity.auditing.TaskAudinting;
import br.com.kproj.salesman.infrastructure.entity.task.Task;
import br.com.kproj.salesman.infrastructure.repository.Pager;
import br.com.kproj.salesman.infrastructure.service.ModelService;
import org.springframework.data.domain.Page;

import java.util.Optional;


public interface TaskAuditingApplication extends ModelService<TaskAudinting> {

    Optional<TaskAudinting> registerAuditing(Task task, User userThatChanged);

    Page<TaskAudinting> findLogs(Long taskId, Pager pager);
}
