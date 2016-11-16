package br.com.kproj.salesman.delivery.delivery.domain.model.user;


import br.com.kproj.salesman.delivery.delivery.domain.model.delivery.Delivery;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;

import java.util.Collection;

public interface WorkerRepository extends BaseRepository<Worker, Long> {

    Worker createWorkerFor(Delivery delivery, Worker worker);

    void remove(Worker worker);

    Collection<Worker> findAll(Delivery delivery);


}
