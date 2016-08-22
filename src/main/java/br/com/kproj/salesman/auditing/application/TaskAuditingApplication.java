package br.com.kproj.salesman.auditing.application;

import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.auditing.TaskAudinting;
import br.com.kproj.salesman.infrastructure.entity.task.Task;
import br.com.kproj.salesman.infrastructure.repository.Pager;
import br.com.kproj.salesman.infrastructure.service.ModelLegacyService;
import org.springframework.data.domain.Page;

import java.util.Optional;


public interface TaskAuditingApplication extends ModelLegacyService<TaskAudinting> {

    Optional<TaskAudinting> registerAuditing(Task task, UserEntity userThatChanged);

    Page<TaskAudinting> findLogs(Long taskId, Pager pager);
}
