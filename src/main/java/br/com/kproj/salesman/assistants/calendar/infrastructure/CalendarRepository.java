package br.com.kproj.salesman.assistants.calendar.infrastructure;


import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.Calendar;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CalendarRepository extends BaseRepository<Calendar, Long> {

    @Query("SELECT c FROM Calendar AS c WHERE c.user = :user")
    Optional<Calendar> findByUser(@Param("user") User user);

}
