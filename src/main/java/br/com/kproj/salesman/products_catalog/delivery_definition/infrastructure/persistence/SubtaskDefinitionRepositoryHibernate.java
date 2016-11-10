package br.com.kproj.salesman.products_catalog.delivery_definition.infrastructure.persistence;

import br.com.kproj.salesman.infrastructure.entity.task_definitions.SubtaskDefinitionEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.RootTask;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.Subtask;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.SubtaskRepository;
import br.com.kproj.salesman.products_catalog.delivery_definition.infrastructure.persistence.springdata.SubtaskDefinitionRepositorySpringData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository("subtaskDefinitionRepositoryModule")
public class SubtaskDefinitionRepositoryHibernate extends BaseRespositoryImpl<Subtask, SubtaskDefinitionEntity> implements SubtaskRepository {


    private SubtaskDefinitionRepositorySpringData repository;

    @Autowired
    public SubtaskDefinitionRepositoryHibernate(SubtaskDefinitionRepositorySpringData repository) {
        this.repository = repository;
    }

    @Override
    public Collection<Subtask> findAll(RootTask rootTask) {
        return null;
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
        return null;
    }

    @Override
    public Converter<SubtaskDefinitionEntity, Subtask> getConverter() {
        return null;
    }
}
