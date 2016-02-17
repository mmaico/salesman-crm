package br.com.kproj.salesman.assistants.calendar.infrastructure;


import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.Calendar;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;

import java.util.Optional;

public interface CalendarRepository extends BaseRepository<Calendar, Long> {

    Optional<Calendar> findByUser(User user);

}
