package br.com.kproj.salesman.assistants.calendar.infrastructure.persistence.springdata;


import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.CalendarActivityEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.stereotype.Repository;

@Repository("calendarActivityRepositoryCalendarModule")
public interface CalendarActivityRepository extends BaseRepositoryLegacy<CalendarActivityEntity, Long> {



}
