package br.com.kproj.salesman.negotiation.negotiation.infrastructure.persistence.springdata;

import br.com.kproj.salesman.infrastructure.entity.accounts.CustomerEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.stereotype.Repository;

@Repository("customerEntityRepositorySpringDataNegotiationModule")
public interface CustomerEntityRepositorySpringData extends BaseRepositoryLegacy<CustomerEntity, Long> {


}
