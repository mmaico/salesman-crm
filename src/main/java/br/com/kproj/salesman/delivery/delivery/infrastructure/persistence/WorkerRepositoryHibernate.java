package br.com.kproj.salesman.delivery.delivery.infrastructure.persistence;

import br.com.kproj.salesman.delivery.delivery.domain.model.delivery.Delivery;
import br.com.kproj.salesman.delivery.delivery.domain.model.user.User;
import br.com.kproj.salesman.delivery.delivery.domain.model.user.Worker;
import br.com.kproj.salesman.delivery.delivery.domain.model.user.WorkerRepository;
import br.com.kproj.salesman.delivery.delivery.infrastructure.persistence.springdata.WorkerRepositorySpringdata;
import br.com.kproj.salesman.delivery.delivery.infrastructure.persistence.translate.WorkerEntityToWorkerConverter;
import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.delivery.DeliveryEntity;
import br.com.kproj.salesman.infrastructure.entity.delivery.WorkerEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.stream.Collectors;

@Repository("workerRepositoryHibernateDeliveryModule")
public class WorkerRepositoryHibernate extends BaseRespositoryImpl<Worker, WorkerEntity> implements WorkerRepository {


    private WorkerRepositorySpringdata workerRepository;

    private WorkerEntityToWorkerConverter converter;


    @Autowired
    public WorkerRepositoryHibernate(WorkerRepositorySpringdata workerRepository, WorkerEntityToWorkerConverter converter) {
        this.workerRepository = workerRepository;
        this.converter = converter;
    }

    @Override
    public Worker createWorkerFor(Delivery delivery, Worker worker) {
        WorkerEntity workerEntity = new WorkerEntity();

        workerEntity.setDelivery(new DeliveryEntity(delivery.getId()));
        workerEntity.setUser(new UserEntity(worker.getUser().getId()));
        WorkerEntity worderCreated = workerRepository.save(workerEntity);

        return this.getConverter().convert(worderCreated);
    }

    @Override
    public void remove(Worker worker) {
        workerRepository.delete(worker.getId());
    }

    @Override
    public Collection<Worker> findAll(Delivery delivery) {
        Collection<WorkerEntity> result = workerRepository.findAll(delivery.getId());

        return result.stream().map(entity -> getConverter().convert(entity))
                .collect(Collectors.toList());
    }

    @Override
    public BaseRepositoryLegacy<WorkerEntity, Long> getRepository() {
        return workerRepository;
    }

    @Override
    public Converter<WorkerEntity, Worker> getConverter() {
        return converter;
    }

}
