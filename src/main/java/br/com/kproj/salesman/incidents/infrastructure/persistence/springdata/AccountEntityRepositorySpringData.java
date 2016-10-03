package br.com.kproj.salesman.incidents.infrastructure.persistence.springdata;

import br.com.kproj.salesman.infrastructure.entity.accounts.AccountEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.stereotype.Repository;

@Repository("accountEntityRepositorySpringDataIncidentsModule")
public interface AccountEntityRepositorySpringData extends BaseRepositoryLegacy<AccountEntity, Long> {



}
