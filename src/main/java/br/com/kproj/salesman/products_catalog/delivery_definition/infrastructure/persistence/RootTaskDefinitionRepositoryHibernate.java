package br.com.kproj.salesman.products_catalog.delivery_definition.infrastructure.persistence;

import br.com.kproj.salesman.infrastructure.entity.OperationRegionEntity;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.infrastructure.entity.task_definitions.RootTaskDefinitionEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.product.Saleable;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.region.Region;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.RootTask;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.RootTaskRepository;
import br.com.kproj.salesman.products_catalog.delivery_definition.infrastructure.persistence.springdata.RootTaskDefinitionRepositorySpringData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.trex.clone.BusinessModelClone.from;

@Repository("taskTemplateRepositoryTaskTemplateModule")
public class RootTaskDefinitionRepositoryHibernate extends BaseRespositoryImpl<RootTask, RootTaskDefinitionEntity> implements RootTaskRepository {


    private RootTaskDefinitionRepositorySpringData repository;

    @Autowired
    public RootTaskDefinitionRepositoryHibernate(RootTaskDefinitionRepositorySpringData repository) {
        this.repository = repository;
    }

    @Override
    public Collection<RootTask> findAll(Saleable saleable) {
        List<RootTaskDefinitionEntity> result = repository.findRootTask(new SaleableUnitEntity(saleable.getId()));

        return result.stream().map(item -> getConverter().convert(item))
                    .collect(Collectors.toList());
    }

    @Override
    public Collection<RootTask> findAll(Saleable saleable, Region region) {
        List<RootTaskDefinitionEntity> result = repository.findRootTask(new SaleableUnitEntity(saleable.getId()), new OperationRegionEntity(region.getId()));

        return result.stream().map(item -> getConverter().convert(item))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(RootTask task) {
        repository.delete(new RootTaskDefinitionEntity(task.getId()));
    }

    @Override
    public Optional<RootTask> add(RootTask task, Saleable saleable) {
        task.setSaleable(saleable);
        RootTaskDefinitionEntity taskTemplateToSave = from(task).convertTo(RootTaskDefinitionEntity.class);
        RootTaskDefinitionEntity taskTemplateSaved = repository.save(taskTemplateToSave);
        return Optional.ofNullable(getConverter().convert(taskTemplateSaved));
    }

    @Override
    public BaseRepositoryLegacy<RootTaskDefinitionEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<RootTaskDefinitionEntity, RootTask> getConverter() {
        return (rootTaskDefinitionEntity, args) -> {
            RootTask task = new RootTask();
            task.setId(rootTaskDefinitionEntity.getId());
            task.setTitle(rootTaskDefinitionEntity.getTitle());
            task.setDescription(rootTaskDefinitionEntity.getDescription());
            task.setRegion(new Region(rootTaskDefinitionEntity.getId()));
            //task.setSaleable(new Saleable());
            //terminar o converter

            return task;
        };
    }
}
