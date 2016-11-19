package br.com.kproj.salesman.delivery.tasks.infrastructure.persistence;

import br.com.kproj.salesman.delivery.tasks.domain.model.delivery.Delivery;
import br.com.kproj.salesman.delivery.tasks.domain.model.sales.CouldNotGenerateTasksException;
import br.com.kproj.salesman.delivery.tasks.domain.model.sales.SalesOrder;
import br.com.kproj.salesman.delivery.tasks.domain.model.subscribe.ChangeStatus;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.Represent;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.Task;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.TaskRepository;
import br.com.kproj.salesman.delivery.tasks.infrastructure.persistence.generatebysalesorder.SalesOrderTaskItemProcessor;
import br.com.kproj.salesman.delivery.tasks.infrastructure.persistence.springdata.TaskRepositorySpringData;
import br.com.kproj.salesman.delivery.tasks.infrastructure.persistence.translate.TaskEntityToTaskConverter;
import br.com.kproj.salesman.infrastructure.entity.enums.TaskStatusEntity;
import br.com.kproj.salesman.infrastructure.entity.task.TaskEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.trex.clone.BusinessModelClone.from;


@Repository
public class TaskRepositoryHibernate extends BaseRespositoryImpl<Task, TaskEntity> implements TaskRepository {

    @Autowired
    private TaskRepositorySpringData repository;

    @Autowired
    private SalesOrderTaskItemProcessor processor;

    @Autowired
    private TaskEntityToTaskConverter converter;


    @Override
    public Optional<Task> save(Task task) {

        TaskEntity taskToSave = from(task).convertTo(TaskEntity.class);

        if (task.isNew()) {
            taskToSave.setStatus(TaskStatusEntity.WAITING);
            TaskEntity tasksaved = repository.save(taskToSave);
            return Optional.of(getConverter().convert(tasksaved));
        } else {
            TaskEntity tasksaved = repository.save(taskToSave);
            return Optional.of(getConverter().convert(tasksaved));
        }
    }

    @Override
    public Iterable<Task> findAll(Delivery delivery, Pageable pageable) {

        Page<TaskEntity> entities = repository.findAllByDelivery(delivery.getId(), pageable);

        List<Task> tasks = entities.getContent().stream().map(entity -> getConverter().convert(entity))
                .collect(Collectors.toList());

        return new PageImpl<>(tasks, pageable, entities.getTotalElements());
    }

    @Override
    public void generateTaskFor(SalesOrder salesOrder) {
        try {
            List<TaskEntity> tasks = processor.process(salesOrder);
            repository.save(tasks);
        } catch (Exception e) {
            throw new CouldNotGenerateTasksException(e);
        }
    }

    @Override
    public void changeStatus(ChangeStatus changeStatus) {

        TaskEntity taskEntity = this.repository.findOne(changeStatus.getTaskId());

        TaskStatusEntity newStatus = TaskStatusEntity.get(changeStatus.getNewStatus().name());
        taskEntity.setStatus(newStatus);
    }


    @Override
    public BaseRepositoryLegacy<TaskEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<TaskEntity, Task> getConverter() {
        return converter;
    }
}
