package br.com.kproj.salesman.delivery.tasks.infrastructure.persistence;

import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.roottask.RootTask;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.roottask.RootTaskRepository;
import br.com.kproj.salesman.delivery.tasks.infrastructure.persistence.springdata.RootTaskRepositorySpringData;
import br.com.kproj.salesman.infrastructure.entity.task.RootTaskEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class RootTaskRepositoryHibernate extends BaseRespositoryImpl<RootTask, RootTaskEntity> implements RootTaskRepository {

    @Autowired
    private RootTaskRepositorySpringData repository;

    @Override
    public void delete(RootTask task) {

    }

    @Override
    public BaseRepositoryLegacy<RootTaskEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<RootTaskEntity, RootTask> getConverter() {
        return null;
    }
}
