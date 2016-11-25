package br.com.kproj.salesman.accounts.addresses.infrastructure.persistence.springdata;

import br.com.kproj.salesman.infrastructure.entity.accounts.CustomerEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.stereotype.Repository;

@Repository("CustomerEntityRepositorySpringDataAddressModule")
public interface CustomerEntityRepositorySpringData extends BaseRepositoryLegacy<CustomerEntity, Long> {




}
