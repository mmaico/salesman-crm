package br.com.kproj.salesman.products_catalog.delivery_definition.infrastructure.persistence;

import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.infrastructure.entity.task_definitions.TaskDefinitionEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.product.Saleable;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.Task;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.TaskRepository;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.TaskToSaleable;
import br.com.kproj.salesman.products_catalog.delivery_definition.infrastructure.persistence.springdata.TaskDefinitionRepositorySpringData;
import br.com.kproj.salesman.products_catalog.delivery_definition.infrastructure.persistence.translate.TaskDefinitionEntityToTaskConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.github.mmaico.clone.BusinessModelClone.from;

@Repository("taskDefinitionRepositoryModule")
public class TaskDefinitionRepositoryHibernate extends BaseRespositoryImpl<Task, TaskDefinitionEntity> implements TaskRepository {


    private TaskDefinitionRepositorySpringData repository;

    private TaskDefinitionEntityToTaskConverter converter;

    @Autowired
    public TaskDefinitionRepositoryHibernate(TaskDefinitionRepositorySpringData repository,
                                             TaskDefinitionEntityToTaskConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public BaseRepositoryLegacy<TaskDefinitionEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<TaskDefinitionEntity, Task> getConverter() {
        return converter;
    }

    @Override
    public Collection<Task> findAll(Saleable saleable) {
        List<TaskDefinitionEntity> entities = repository.findAll(new SaleableUnitEntity(saleable.getId()));
        List<Task> tasks = entities.stream().map(entity -> getConverter().convert(entity))
                .collect(Collectors.toList());
        return tasks;
    }

    @Override
    public Optional<Task> create(TaskToSaleable taskToSaleable) {
        TaskDefinitionEntity taskDefinitionEntity = from(taskToSaleable.getTask()).convertTo(TaskDefinitionEntity.class);
        taskDefinitionEntity.setSaleable(new SaleableUnitEntity(taskToSaleable.getSaleableId()));

        TaskDefinitionEntity entitySaved = repository.save(taskDefinitionEntity);

        return Optional.of(getConverter().convert(entitySaved));
    }

    @Override
    public Boolean hasSpecialization(Long id) {
        return repository.findOne(id).getType() != null;
    }
}
