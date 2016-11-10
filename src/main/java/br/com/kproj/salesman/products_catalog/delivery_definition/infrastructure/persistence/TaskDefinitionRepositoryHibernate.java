package br.com.kproj.salesman.products_catalog.delivery_definition.infrastructure.persistence;

import br.com.kproj.salesman.infrastructure.entity.task_definitions.TaskDefinitionEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.Task;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.TaskRepository;
import br.com.kproj.salesman.products_catalog.delivery_definition.infrastructure.persistence.springdata.TaskDefinitionRepositorySpringData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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



            return task;
        };
    }
}
