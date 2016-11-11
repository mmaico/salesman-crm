package br.com.kproj.salesman.products_catalog.delivery_definition.infrastructure.persistence;

import br.com.kproj.salesman.infrastructure.entity.task_definitions.RootTaskDefinitionEntity;
import br.com.kproj.salesman.infrastructure.entity.task_definitions.SubtaskDefinitionEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.product.Saleable;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.RootTask;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.RootTaskRepository;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.Subtask;
import br.com.kproj.salesman.products_catalog.delivery_definition.infrastructure.persistence.springdata.RootTaskDefinitionRepositorySpringData;
import br.com.kproj.salesman.products_catalog.delivery_definition.infrastructure.persistence.springdata.SubtaskDefinitionRepositorySpringData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.trex.clone.BusinessModelClone.from;

@Repository("rootTaskDefinitionRepositoryModule")
public class RootTaskDefinitionRepositoryHibernate extends BaseRespositoryImpl<RootTask, RootTaskDefinitionEntity> implements RootTaskRepository {


    private RootTaskDefinitionRepositorySpringData repository;

    private SubtaskDefinitionRepositorySpringData subtaskRepository;

    @Autowired
    public RootTaskDefinitionRepositoryHibernate(RootTaskDefinitionRepositorySpringData repository,
                                                 SubtaskDefinitionRepositorySpringData subtaskRepository) {
        this.repository = repository;
        this.subtaskRepository = subtaskRepository;
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
            RootTask rootTask = from(rootTaskDefinitionEntity).convertTo(RootTask.class);
            rootTask.setSaleable(new Saleable(rootTaskDefinitionEntity.getId()));

            List<SubtaskDefinitionEntity> subtasks = subtaskRepository.findRootTask(rootTaskDefinitionEntity);
            subtasks.stream().forEach(subtask -> rootTask.getChildren().add(from(subtask).convertTo(Subtask.class)));

            return rootTask;
        };
    }
}
