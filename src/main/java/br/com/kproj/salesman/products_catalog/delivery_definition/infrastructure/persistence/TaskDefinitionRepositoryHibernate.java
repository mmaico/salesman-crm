package br.com.kproj.salesman.products_catalog.delivery_definition.infrastructure.persistence;

import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.infrastructure.entity.task_definitions.TaskDefinitionEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.product.Saleable;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.region.Region;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.Represent;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.Task;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.TaskRepository;
import br.com.kproj.salesman.products_catalog.delivery_definition.infrastructure.persistence.springdata.TaskDefinitionRepositorySpringData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.trex.clone.BusinessModelClone.from;

@Repository("taskDefinitionRepositoryModule")
public class TaskDefinitionRepositoryHibernate extends BaseRespositoryImpl<Task, TaskDefinitionEntity> implements TaskRepository {


    private TaskDefinitionRepositorySpringData repository;

    @Autowired
    public TaskDefinitionRepositoryHibernate(TaskDefinitionRepositorySpringData repository) {
        this.repository = repository;
    }

    @Override
    public BaseRepositoryLegacy<TaskDefinitionEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<TaskDefinitionEntity, Task> getConverter() {
        return (taskDefinitionEntity, args) -> {
            Task task = from(taskDefinitionEntity).convertTo(Task.class);
            task.setRegion(new Region(taskDefinitionEntity.getRegion().getId()));
            task.setSaleable(new Saleable(taskDefinitionEntity.getSaleable().getId()));

            Optional<Represent> represent = taskDefinitionEntity.getType() == null
                    ? Optional.of(Represent.NO_REPRESENT)
                    : Optional.ofNullable(Represent.valueOf(taskDefinitionEntity.getType().name()));

            task.setRepresent(represent.orElse(Represent.NO_REPRESENT));

            return task;
        };
    }

    @Override
    public Collection<Task> findAll(Saleable saleable) {
        List<TaskDefinitionEntity> entities = repository.findAll(new SaleableUnitEntity(saleable.getId()));
        List<Task> tasks = entities.stream().map(entity -> getConverter().convert(entity))
                .collect(Collectors.toList());
        return tasks;
    }
}
