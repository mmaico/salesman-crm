package br.com.kproj.salesman.assistants.calendar.activities.activity.domain.model.calendar;


import br.com.kproj.salesman.assistants.calendar.domain.model.user.User;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;

import java.util.Optional;

public interface CalendarRepository extends BaseRepository<Calendar, Long> {


    Optional<Calendar> registerFor(User user);

    Boolean hasFor(User user);

}
