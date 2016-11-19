package br.com.kproj.salesman.delivery.tasks.infrastructure.persistence;

import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.roottask.RootTask;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.subtask.Subtask;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.subtask.SubtaskRepository;
import br.com.kproj.salesman.delivery.tasks.infrastructure.persistence.springdata.SubtaskRepositorySpringData;
import br.com.kproj.salesman.infrastructure.entity.task.SubtaskEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;


@Repository
public class SubtaskRepositoryHibernate extends BaseRespositoryImpl<Subtask, SubtaskEntity> implements SubtaskRepository {


    private SubtaskRepositorySpringData repository;

    @Autowired
    public SubtaskRepositoryHibernate(SubtaskRepositorySpringData repository) {
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
    public BaseRepositoryLegacy<SubtaskEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<SubtaskEntity, Subtask> getConverter() {
        return null;
    }
}
