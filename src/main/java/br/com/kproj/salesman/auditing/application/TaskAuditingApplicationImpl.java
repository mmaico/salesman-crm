package br.com.kproj.salesman.auditing.application;

import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.auditing.TaskAudinting;
import br.com.kproj.salesman.infrastructure.entity.builders.TaskAuditingBuilder;
import br.com.kproj.salesman.infrastructure.entity.task.Task;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.Pager;
import br.com.kproj.salesman.infrastructure.repository.TaskAuditingRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceLegacyImpl;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskAuditingApplicationImpl extends BaseModelServiceLegacyImpl<TaskAudinting> implements TaskAuditingApplication {


    @Autowired
    private TaskAuditingRepository repository;

    @Autowired
    private Gson gson;


    @Override
    public Optional<TaskAudinting> registerAuditing(Task task, User userThatChanged) {
        Page<TaskAudinting> lasModitication = repository.findLasVersion(task.getId(), Pager.build().withPageSize(1));

        TaskAudinting newEntryAuditable = TaskAuditingBuilder.createAuditing()
                .withEntityId(task.getId())
                .setCurrentDate()
                .withInfo(gson.toJson(task))
                .withUser(userThatChanged).build();

        if (lasModitication.getContent().size() == 0) {
            return Optional.ofNullable(repository.save(newEntryAuditable));
        } else {
            TaskAudinting before = lasModitication.getContent().get(0);
            if (!before.getInfo().equals(newEntryAuditable.getInfo())) {
                TaskAudinting after = repository.save(newEntryAuditable);

                return Optional.ofNullable(after);
            }
        }

        return Optional.empty();
    }

    @Override
    public Page<TaskAudinting> findLogs(Long taskId, Pager pager) {
        return repository.findAll(taskId, pager);
    }

    public BaseRepositoryLegacy<TaskAudinting, Long> getRepository() {
        return this.repository;
    }
}
