package br.com.kproj.salesman.assistants.calendar.activities.activity.infrastructure.persistence.springdata;


import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.CalendarActivityEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.stereotype.Repository;

@Repository("calendarRepositoryCalendarModule")
public interface CalendarActivityRepository extends BaseRepositoryLegacy<CalendarActivityEntity, Long> {



}
