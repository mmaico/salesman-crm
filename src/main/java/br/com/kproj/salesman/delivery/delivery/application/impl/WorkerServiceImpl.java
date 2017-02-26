package br.com.kproj.salesman.delivery.delivery.application.impl;


import br.com.kproj.salesman.delivery.delivery.application.WorkerFacade;
import br.com.kproj.salesman.delivery.delivery.domain.model.delivery.Delivery;
import br.com.kproj.salesman.delivery.delivery.domain.model.user.*;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service("workerFacadeDeliveryModule")
public class WorkerServiceImpl extends BaseModelServiceImpl<Worker> implements WorkerFacade {

    private WorkerRepository repository;
    private WorkerValidator validator;

    @Autowired
    public WorkerServiceImpl(WorkerRepository repository, WorkerValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    @Override
    public Worker register(WorkerIn workerIn) {
        Delivery delivery = workerIn.getDelivery();

        Worker worker = WorkerBuilder.createWorker()
                .withDelivery(workerIn.getDeliveryId())
                .withUser(workerIn.getUserId()).build();

        validator.checkRules(worker);

        return delivery.addNewWorker(worker);
    }

    @Override
    public void delete(WorkerOut workerOut) {
        Delivery delivery = new Delivery();
        Worker worker = new Worker(workerOut.getWorkerId());

        delivery.removeWorker(worker);
    }

    @Override
    public Collection<Worker> findAll(Delivery delivery) {
        if (delivery.isNew()) {
            throw new ValidationException("timeline.with.invalid.id");
        }
        return repository.findAll(delivery);
    }


    @Override
    public BaseRepository<Worker, Long> getRepository() {
        return repository;
    }


}
