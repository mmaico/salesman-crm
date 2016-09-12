package br.com.kproj.salesman.delivery2.tasks_template.infrastructure.persistence;

import br.com.kproj.salesman.delivery2.tasks_template.infrastructure.persistence.springdata.TaskTemplate2RepositorySpringData;
import br.com.kproj.salesman.delivery2.tasks_template.model.product.Saleable;
import br.com.kproj.salesman.delivery2.tasks_template.model.region.Region;
import br.com.kproj.salesman.delivery2.tasks_template.model.tasks.Task;
import br.com.kproj.salesman.delivery2.tasks_template.model.tasks.TaskRepository;
import br.com.kproj.salesman.infrastructure.entity.OperationRegionEntity;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.infrastructure.entity.task.TaskTemplateEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.trex.clone.BusinessModelClone.from;

@Repository("taskTemplateRepository")
public class TaskTemplateRepositoryHibernate extends BaseRespositoryImpl<Task, TaskTemplateEntity> implements TaskRepository {

    @Autowired
    private TaskTemplate2RepositorySpringData repository;


    @Override
    public Collection<Task> findAll(Saleable saleable) {
        List<TaskTemplateEntity> result = repository.findTaskTemplateBy(new SaleableUnitEntity(saleable.getId()));

        return result.stream().map(item -> getConverter().convert(item))
                    .collect(Collectors.toList());
    }

    @Override
    public Collection<Task> findAll(Saleable saleable, Region region) {
        List<TaskTemplateEntity> result = repository.findTaskTemplateBy(new SaleableUnitEntity(saleable.getId()),
                new OperationRegionEntity(region.getId()));

        return result.stream().map(item -> getConverter().convert(item))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Task task) {
        repository.delete(new TaskTemplateEntity(task.getId()));
    }

    @Override
    public Optional<Task> add(Task task, Saleable saleable) {
        task.setSaleable(saleable);
        TaskTemplateEntity taskTemplateToSave = from(task).convertTo(TaskTemplateEntity.class);
        TaskTemplateEntity taskTemplateSaved = repository.save(taskTemplateToSave);
        return Optional.ofNullable(getConverter().convert(taskTemplateSaved));
    }

    @Override
    public BaseRepositoryLegacy<TaskTemplateEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<TaskTemplateEntity, Task> getConverter() {
        return (taskTemplateEntity, args) -> {
            Task task = new Task();
            task.setId(taskTemplateEntity.getId());
            task.setTitle(taskTemplateEntity.getTitle());
            task.setDescription(taskTemplateEntity.getDescription());
            //terminar o converter

            return task;
        };
    }
}
