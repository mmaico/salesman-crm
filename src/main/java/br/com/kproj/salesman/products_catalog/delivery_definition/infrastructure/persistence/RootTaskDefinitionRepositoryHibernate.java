package br.com.kproj.salesman.products_catalog.delivery_definition.infrastructure.persistence;

import br.com.kproj.salesman.infrastructure.entity.OperationRegionEntity;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.infrastructure.entity.task.TaskTemplateEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.product.Saleable;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.region.Region;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.RootTask;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.RootTaskRepository;
import br.com.kproj.salesman.products_catalog.delivery_definition.infrastructure.persistence.springdata.TaskTemplate2RepositorySpringData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.trex.clone.BusinessModelClone.from;

@Repository("taskTemplateRepositoryTaskTemplateModule")
public class RootTaskDefinitionRepositoryHibernate extends BaseRespositoryImpl<RootTask, TaskTemplateEntity> implements RootTaskRepository {


    private TaskTemplate2RepositorySpringData repository;

    @Autowired
    public RootTaskDefinitionRepositoryHibernate(TaskTemplate2RepositorySpringData repository) {
        this.repository = repository;
    }

    @Override
    public Collection<RootTask> findAll(Saleable saleable) {
        List<TaskTemplateEntity> result = repository.findTaskTemplateBy(new SaleableUnitEntity(saleable.getId()));

        return result.stream().map(item -> getConverter().convert(item))
                    .collect(Collectors.toList());
    }

    @Override
    public Collection<RootTask> findAll(Saleable saleable, Region region) {
        List<TaskTemplateEntity> result = repository.findTaskTemplateBy(new SaleableUnitEntity(saleable.getId()),
                new OperationRegionEntity(region.getId()));

        return result.stream().map(item -> getConverter().convert(item))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(RootTask task) {
        repository.delete(new TaskTemplateEntity(task.getId()));
    }

    @Override
    public Optional<RootTask> add(RootTask task, Saleable saleable) {
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
    public Converter<TaskTemplateEntity, RootTask> getConverter() {
        return (taskTemplateEntity, args) -> {
            RootTask task = new RootTask();
            task.setId(taskTemplateEntity.getId());
            task.setTitle(taskTemplateEntity.getTitle());
            task.setDescription(taskTemplateEntity.getDescription());
            //terminar o converter

            return task;
        };
    }
}
