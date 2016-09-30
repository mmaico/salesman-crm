package br.com.kproj.salesman.assistants.activities.infrastructure.persistence.springdata;

import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.stereotype.Repository;

@Repository("userEntityRepositoryActivitiesModule")
public interface UserEntityRepository extends BaseRepositoryLegacy<UserEntity, Long> {


}
