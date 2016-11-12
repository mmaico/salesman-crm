package br.com.kproj.salesman.products_catalog.delivery_definition.infrastructure.persistence;

import br.com.kproj.salesman.infrastructure.entity.task_definitions.RootTaskDefinitionEntity;
import br.com.kproj.salesman.infrastructure.entity.task_definitions.SubtaskDefinitionEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.RootTask;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.Subtask;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.SubtaskRepository;
import br.com.kproj.salesman.products_catalog.delivery_definition.infrastructure.persistence.springdata.SubtaskDefinitionRepositorySpringData;
import br.com.kproj.salesman.products_catalog.delivery_definition.infrastructure.persistence.translate.SubtaskDefinitionEntityToSubTaskConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository("subtaskDefinitionRepositoryModule")
public class SubtaskDefinitionRepositoryHibernate extends BaseRespositoryImpl<Subtask, SubtaskDefinitionEntity> implements SubtaskRepository {


    private SubtaskDefinitionRepositorySpringData repository;

    private SubtaskDefinitionEntityToSubTaskConverter converter;

    @Autowired
    public SubtaskDefinitionRepositoryHibernate(SubtaskDefinitionRepositorySpringData repository,
                                                SubtaskDefinitionEntityToSubTaskConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public Collection<Subtask> findAll(RootTask rootTask) {
        List<SubtaskDefinitionEntity> entities = repository.findAll(new RootTaskDefinitionEntity(rootTask.getId()));
        List<Subtask> subtasks = entities.stream().map(entity -> converter.convert(entity))
                .collect(Collectors.toList());

        return subtasks;
    }

    @Override
    public void delete(Subtask task) {

    }

    @Override
    public Optional<Subtask> add(Subtask subtask, RootTask task) {
        return null;
    }

    @Override
    public BaseRepositoryLegacy<SubtaskDefinitionEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<SubtaskDefinitionEntity, Subtask> getConverter() {
        return converter;
    }
}
