package br.com.kproj.salesman.delivery2.tasks.infrastructure.persistence;

import br.com.kproj.salesman.delivery2.tasks.domain.model.sales.CouldNotGenerateTasksException;
import br.com.kproj.salesman.delivery2.tasks.domain.model.sales.SalesOrder;
import br.com.kproj.salesman.delivery2.tasks.domain.model.tasks.Subtask;
import br.com.kproj.salesman.delivery2.tasks.domain.model.tasks.Task;
import br.com.kproj.salesman.delivery2.tasks.domain.model.tasks.TaskRepository;
import br.com.kproj.salesman.delivery2.tasks.infrastructure.persistence.generatebysalesorder.SalesOrderTaskItemProcessor;
import br.com.kproj.salesman.delivery2.tasks.infrastructure.persistence.springdata.TaskRepositorySpringData;
import br.com.kproj.salesman.infrastructure.entity.enums.TaskStatus;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrderEntity;
import br.com.kproj.salesman.infrastructure.entity.task.TaskEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.helpers.ReflectionsHelper.copyProperties;
import static com.trex.clone.BusinessModelClone.from;


@Repository
public class TaskRepositoryHibernate extends BaseRespositoryImpl<Task, TaskEntity> implements TaskRepository {

    @Autowired
    private TaskRepositorySpringData repository;

    @Autowired
    private SalesOrderTaskItemProcessor processor;


    @Override
    public Optional<Task> save(Task task) {

        TaskEntity taskToSave = from(task).convertTo(TaskEntity.class);

        if (task.isNew()) {
            taskToSave.setStatus(TaskStatus.WAITING);
            TaskEntity tasksaved = repository.save(taskToSave);
            return Optional.of(getConverter().convert(tasksaved));
        } else {
            TaskEntity tasksaved = repository.save(taskToSave);
            return Optional.of(getConverter().convert(tasksaved));
        }
    }

    public Optional<Subtask> save(Subtask subtask) {

        Task parent = subtask.getParent();
        TaskEntity taskEntityParent = repository.findOne(parent.getId());

        TaskEntity taskToSave = from(subtask).convertTo(TaskEntity.class);
        taskToSave.setSalesOrderEntity(taskEntityParent.getSalesOrderEntity());

        TaskEntity tasksaved = repository.save(taskToSave);
        taskEntityParent.addChild(tasksaved);

        Task taskConverted = getConverter().convert(tasksaved);
        Subtask subtaskSaved = new Subtask();

        copyProperties(subtaskSaved, taskConverted);
        subtaskSaved.setParent(parent);

        return Optional.of(subtaskSaved);
    }

    @Override
    public Collection<Task> findAll(SalesOrder salesOrder) {
        SalesOrderEntity orderEntity = new SalesOrderEntity(salesOrder.getId());

        List<TaskEntity> entities = repository.findBySalesOrder(orderEntity);

        List<Task> tasks = entities.stream().map(entity -> getConverter().convert(entity))
                .collect(Collectors.toList());

        return tasks;
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
        return null;
    }
}
