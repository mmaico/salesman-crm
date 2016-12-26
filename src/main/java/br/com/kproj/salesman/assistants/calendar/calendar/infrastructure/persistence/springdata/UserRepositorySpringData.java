package br.com.kproj.salesman.assistants.calendar.calendar.infrastructure.persistence.springdata;

import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.stereotype.Repository;

@Repository("userRepositorySpringDataCalendarModule")
public interface UserRepositorySpringData extends BaseRepositoryLegacy<UserEntity, Long> {


}
