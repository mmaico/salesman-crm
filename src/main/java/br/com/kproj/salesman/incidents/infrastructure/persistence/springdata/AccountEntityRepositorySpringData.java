package br.com.kproj.salesman.incidents.infrastructure.persistence.springdata;

import br.com.kproj.salesman.infrastructure.entity.accounts.CustomerEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.stereotype.Repository;

@Repository("accountEntityRepositorySpringDataIncidentsModule")
public interface AccountEntityRepositorySpringData extends BaseRepositoryLegacy<CustomerEntity, Long> {



}
