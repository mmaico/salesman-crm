package br.com.kproj.salesman.delivery.delivery.domain.model.delivery;


import br.com.kproj.salesman.delivery.delivery.domain.model.user.Worker;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;

public interface DeliveryRepository extends BaseRepository<Delivery, Long> {


    void addWorkerIn(Delivery delivery, Worker worker);

    void removeWorkerFrom(Delivery delivery, Worker worker);
}
