package br.com.kproj.salesman.assistants.calendar.infrastructure.persistence.springdata;


import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.CalendarEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CalendarEntityRepositorySpringdata extends BaseRepositoryLegacy<CalendarEntity, Long> {


    @Query("SELECT c FROM CalendarEntity AS c WHERE c.user.id = :userId")
    Optional<CalendarEntity> hasFor(@Param("userId") Long userId);

}
