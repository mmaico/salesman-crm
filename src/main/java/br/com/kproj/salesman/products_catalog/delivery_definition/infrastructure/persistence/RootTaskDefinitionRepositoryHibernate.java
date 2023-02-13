package br.com.kproj.salesman.products_catalog.delivery_definition.infrastructure.persistence;

import br.com.kproj.salesman.infrastructure.entity.task_definitions.RootTaskDefinitionEntity;
import br.com.kproj.salesman.infrastructure.entity.task_definitions.TaskDefinitionEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.Task;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.roottasks.RootTask;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.roottasks.RootTaskRepository;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.subtasks.Subtask;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.subtasks.SubtaskRepository;
import br.com.kproj.salesman.products_catalog.delivery_definition.infrastructure.persistence.springdata.RootTaskDefinitionRepositorySpringData;
import br.com.kproj.salesman.products_catalog.delivery_definition.infrastructure.persistence.translate.TaskDefinitionEntityToTaskConverter;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

import static com.github.mmaico.clone.BusinessModelClone.from;

@Repository("rootTaskDefinitionRepositoryModule")
public class RootTaskDefinitionRepositoryHibernate extends BaseRespositoryImpl<RootTask, RootTaskDefinitionEntity> implements RootTaskRepository {


    private RootTaskDefinitionRepositorySpringData repository;

    private SubtaskRepository subtaskRepository;

    private TaskDefinitionEntityToTaskConverter converter;

    @Autowired
    public RootTaskDefinitionRepositoryHibernate(RootTaskDefinitionRepositorySpringData repository,
                                                 SubtaskRepository subtaskRepository,
                                                 TaskDefinitionEntityToTaskConverter converter) {
        this.repository = repository;
        this.subtaskRepository = subtaskRepository;
        this.converter = converter;
    }


    @Override
    public void delete(RootTask task) {
        repository.delete(new RootTaskDefinitionEntity(task.getId()));
    }

    public Optional<RootTask> save(RootTask entity) {
        RootTaskDefinitionEntity rootTaskEntity = new RootTaskDefinitionEntity();
        rootTaskEntity.setTaskDefinition(new TaskDefinitionEntity(entity.getId()));
        rootTaskEntity.setId(entity.getId());

        return Optional.of(getConverter().convert(repository.save(rootTaskEntity)));
    }

    @Override
    public BaseRepositoryLegacy<RootTaskDefinitionEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<RootTaskDefinitionEntity, RootTask> getConverter() {
        return (rootTaskDefinitionEntity, args) -> {
            RootTask rootTask = from(rootTaskDefinitionEntity).convertTo(RootTask.class);
            Task taskConverted = converter.convert(rootTaskDefinitionEntity.getTaskDefinition());
            from(taskConverted).merge(rootTask);

            Collection<Subtask> result = subtaskRepository.findAll(rootTask);
            rootTask.setChildren(Lists.newArrayList(result));

            return rootTask;
        };
    }
}
