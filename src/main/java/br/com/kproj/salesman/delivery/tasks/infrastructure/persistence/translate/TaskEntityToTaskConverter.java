package br.com.kproj.salesman.delivery.tasks.infrastructure.persistence.translate;

import br.com.kproj.salesman.delivery.tasks.domain.model.checklist.Checklist;
import br.com.kproj.salesman.delivery.tasks.domain.model.delivery.Delivery;
import br.com.kproj.salesman.delivery.tasks.domain.model.subscribe.Subscriber;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.Represent;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.Task;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.TaskStatus;
import br.com.kproj.salesman.infrastructure.entity.task.TaskEntity;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static br.com.kproj.salesman.delivery.tasks.domain.model.tasks.Represent.NO_REPRESENT;


@Component
public class TaskEntityToTaskConverter implements Converter<TaskEntity, Task> {


    @Override
    public Task convert(TaskEntity taskEntity, Object... args) {

        Task task = new Task(taskEntity.getId());
        task.setDeadline(taskEntity.getDeadline());
        task.setTitle(taskEntity.getTitle());
        task.setDescription(taskEntity.getDescription());
        task.setDelivery(new Delivery(taskEntity.getDelivery().getId()));
        task.setStatus(TaskStatus.get(taskEntity.getStatus().name()));

        Optional<Represent> represent = taskEntity.getType() == null
                ? Optional.of(NO_REPRESENT)
                : Optional.ofNullable(Represent.valueOf(taskEntity.getType().name()));

        task.setRepresent(represent.orElse(Represent.NO_REPRESENT));

        if (taskEntity.getChecklist() != null) {
            taskEntity.getChecklist()
                    .forEach(checklistEntity -> task.addChecklist(new Checklist(checklistEntity.getId())));
        }

        if (taskEntity.getResponsibles() != null) {
            taskEntity.getResponsibles()
                    .forEach(taskResponsibleEntity -> task.addSubscriber(new Subscriber(taskResponsibleEntity.getId())));
        }

        return task;
    }
}
