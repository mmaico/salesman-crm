package br.com.kproj.salesman.assistants.calendar.infrastructure;


import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.CalendarEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CalendarRepository extends BaseRepositoryLegacy<CalendarEntity, Long> {

    @Query("SELECT c FROM CalendarEntity AS c WHERE c.user = :user")
    Optional<CalendarEntity> findByUser(@Param("user") UserEntity user);

}
