package br.com.kproj.salesman.medias.storage.persistence.springdata;

import br.com.kproj.salesman.infrastructure.entity.accounts.CustomerEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.stereotype.Repository;


@Repository("customerEntityRepositorySpringDataContactsModule")
public interface CustomerEntityRepositorySpringData extends BaseRepositoryLegacy<CustomerEntity, Long> {




}
