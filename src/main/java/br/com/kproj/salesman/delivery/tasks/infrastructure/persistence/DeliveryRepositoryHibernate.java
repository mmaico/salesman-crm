package br.com.kproj.salesman.delivery.tasks.infrastructure.persistence;


import br.com.kproj.salesman.delivery.tasks.domain.model.delivery.Delivery;
import br.com.kproj.salesman.delivery.tasks.domain.model.delivery.DeliveryRepository;
import br.com.kproj.salesman.delivery.tasks.infrastructure.persistence.springdata.DeliveryRepositorySpringData;
import br.com.kproj.salesman.infrastructure.entity.delivery.DeliveryEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("deliveryRepositoryHibernateDeliveryTaskModule")
public class DeliveryRepositoryHibernate extends BaseRespositoryImpl<Delivery, DeliveryEntity> implements DeliveryRepository {


    @Autowired
    private DeliveryRepositorySpringData repository;


    @Override
    public BaseRepositoryLegacy<DeliveryEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<DeliveryEntity, Delivery> getConverter() {
        return ((entity, args) -> new Delivery(entity.getId()));
    }
}
