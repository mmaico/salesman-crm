package br.com.kproj.salesman.business_prospecting.leads.infrastructure.persistence.springdata;

import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.stereotype.Repository;

@Repository("userRepositorySpringDataLeadModule")
public interface UserRepositorySpringData extends BaseRepositoryLegacy<UserEntity, Long> {


}
