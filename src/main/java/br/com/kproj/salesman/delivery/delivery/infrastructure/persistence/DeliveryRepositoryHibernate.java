package br.com.kproj.salesman.delivery.delivery.infrastructure.persistence;

import br.com.kproj.salesman.delivery.delivery.domain.model.sales.SalesOrder;
import br.com.kproj.salesman.delivery.delivery.domain.model.user.Worker;
import br.com.kproj.salesman.delivery.delivery.domain.model.delivery.Delivery;
import br.com.kproj.salesman.delivery.delivery.domain.model.delivery.DeliveryRepository;
import br.com.kproj.salesman.delivery.delivery.infrastructure.persistence.springdata.DeliveryRepositorySpringdata;
import br.com.kproj.salesman.delivery.delivery.infrastructure.persistence.translate.WorkerEntityToWorkerConverter;
import br.com.kproj.salesman.infrastructure.entity.WorkspaceUnit;
import br.com.kproj.salesman.infrastructure.entity.delivery.DeliveryEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("deliveryRepositoryHibernateDeliveryModule")
public class DeliveryRepositoryHibernate extends BaseRespositoryImpl<Delivery, DeliveryEntity> implements DeliveryRepository {


    private DeliveryRepositorySpringdata repository;
    private WorkerEntityToWorkerConverter converter;

    @Autowired
    public DeliveryRepositoryHibernate(DeliveryRepositorySpringdata repository, WorkerEntityToWorkerConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public void addWorkerIn(Delivery delivery, Worker worker) {
        DeliveryEntity deliveryEntity = repository.findOne(delivery.getId());

        //WorkspaceUnit workspaceUnit = repository.findOne(delivery.getId());

    }

    @Override
    public void removeWorkerFrom(Delivery delivery, Worker worker) {

    }


    @Override
    public BaseRepositoryLegacy<DeliveryEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<DeliveryEntity, Delivery> getConverter() {
        return (deliveryEntity, args) -> {
            Delivery delivery = new Delivery();
            delivery.setId(deliveryEntity.getId());
            delivery.setSalesOrder(new SalesOrder(deliveryEntity.getSalesOrder().getId()));

            deliveryEntity.getWorkers().stream()
                    .forEach(workerEntity -> delivery.addWorker(converter.convert(workerEntity)));

            return delivery;
        };
    }


}
