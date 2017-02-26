package br.com.kproj.salesman.delivery.tasks.infrastructure.persistence.springdata;

import br.com.kproj.salesman.infrastructure.entity.delivery.DeliveryEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.stereotype.Repository;

@Repository("deliveryRepositorySpringDataTaskModule")
public interface DeliveryRepositorySpringData extends BaseRepositoryLegacy<DeliveryEntity, Long> {


}
