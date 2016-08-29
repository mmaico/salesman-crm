package br.com.kproj.salesman.negotiation2.infrastructure.persistence.springdata;

import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositorySpringData extends BaseRepositoryLegacy<UserEntity, Long> {


}
