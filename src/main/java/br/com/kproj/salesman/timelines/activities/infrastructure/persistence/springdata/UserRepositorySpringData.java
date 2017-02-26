package br.com.kproj.salesman.timelines.activities.infrastructure.persistence.springdata;

import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.stereotype.Repository;

@Repository("userRepositorySpringDataTimelineActivitiesModule")
public interface UserRepositorySpringData extends BaseRepositoryLegacy<UserEntity, Long> {


}
