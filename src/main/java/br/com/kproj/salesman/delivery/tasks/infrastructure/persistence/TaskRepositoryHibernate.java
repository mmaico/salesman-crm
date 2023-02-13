package br.com.kproj.salesman.delivery.tasks.infrastructure.persistence;

import br.com.kproj.salesman.delivery.tasks.domain.model.delivery.Delivery;
import br.com.kproj.salesman.delivery.tasks.domain.model.sales.CouldNotGenerateTasksException;
import br.com.kproj.salesman.delivery.tasks.domain.model.sales.SalesOrder;
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

import static com.github.mmaico.clone.BusinessModelClone.from;


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

        if (task.isNew()) {
            TaskEntity taskToSave = from(task).convertTo(TaskEntity.class);
            taskToSave.setStatus(TaskStatusEntity.WAITING);
            TaskEntity tasksaved = repository.save(taskToSave);

            return Optional.of(getConverter().convert(tasksaved));
        } else {
            TaskEntity taskEntity = repository.findOne(task.getId());
            from(task).merge(taskEntity);
            repository.save(taskEntity);
            return Optional.of(getConverter().convert(taskEntity));
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
    public BaseRepositoryLegacy<TaskEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<TaskEntity, Task> getConverter() {
        return converter;
    }
}
