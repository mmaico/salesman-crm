package br.com.kproj.salesman.assistants.calendar.activities.activity.infrastructure.persistence.springdata;


import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.CalendarEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.stereotype.Repository;

@Repository("calendarEntityRepositorySpringdataActivity")
public interface CalendarEntityRepositorySpringdata extends BaseRepositoryLegacy<CalendarEntity, Long> {


}
