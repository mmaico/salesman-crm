package br.com.kproj.salesman.assistants.calendar.infrastructure.persistence.springdata;


import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.activity_specialization.CalendarActivitySaleableEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.stereotype.Repository;

@Repository("calendarSaleableRepositoryCalendarModule")
public interface CalendarActivitySaleableRepository extends BaseRepositoryLegacy<CalendarActivitySaleableEntity, Long> {



}
