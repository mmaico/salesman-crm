package br.com.kproj.salesman.assistants.calendar.activities.activity.infrastructure.persistence.springdata;


import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.CalendarEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("calendarEntityRepositorySpringdataActivity")
public interface CalendarEntityRepositorySpringdata extends BaseRepositoryLegacy<CalendarEntity, Long> {


}
