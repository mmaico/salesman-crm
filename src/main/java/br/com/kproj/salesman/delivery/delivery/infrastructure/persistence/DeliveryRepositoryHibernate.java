package br.com.kproj.salesman.delivery.delivery.infrastructure.persistence;

import br.com.kproj.salesman.delivery.delivery.domain.model.user.Worker;
import br.com.kproj.salesman.delivery.delivery.domain.model.delivery.Delivery;
import br.com.kproj.salesman.delivery.delivery.domain.model.delivery.DeliveryRepository;
import br.com.kproj.salesman.delivery.delivery.infrastructure.persistence.springdata.DeliveryRepositorySpringdata;
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

    @Autowired
    public DeliveryRepositoryHibernate(DeliveryRepositorySpringdata repository) {
        this.repository = repository;
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

            //terminar o converter

            return null;
        };
    }


}
