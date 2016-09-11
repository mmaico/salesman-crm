package br.com.kproj.salesman.auditing.infrastructure.helpers;

import br.com.kproj.salesman.infrastructure.entity.auditing.TaskAudinting;
import br.com.kproj.salesman.infrastructure.entity.task.TaskEntity;
import br.com.kproj.salesman.infrastructure.repository.Pager;
import br.com.kproj.salesman.infrastructure.repository.TaskAuditingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskAuditingHelper {

    @Autowired
    private TaskAuditingRepository repository;

    public Iterable<TaskAudinting> findBy(TaskEntity taskEntity, Pager pager) {

        return repository.findAll(taskEntity.getId(), pager);
    }
}
