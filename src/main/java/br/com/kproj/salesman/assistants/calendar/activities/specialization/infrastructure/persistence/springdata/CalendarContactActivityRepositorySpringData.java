package br.com.kproj.salesman.assistants.calendar.activities.specialization.infrastructure.persistence.springdata;


import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.activity_specialization.CalendarActivityContactEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.stereotype.Repository;

@Repository("calendarActivityContactRepositoryCalendarModule")
public interface CalendarContactActivityRepositorySpringData extends BaseRepositoryLegacy<CalendarActivityContactEntity, Long> {



}
