package br.com.kproj.salesman.assistants.calendar.activities.specialization.infrastructure.persistence.springdata;


import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.CalendarActivityEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.stereotype.Repository;

@Repository("calendarRepositoryCalendarActivityModule")
public interface CalendarActivityRepositorySpringData extends BaseRepositoryLegacy<CalendarActivityEntity, Long> {



}
