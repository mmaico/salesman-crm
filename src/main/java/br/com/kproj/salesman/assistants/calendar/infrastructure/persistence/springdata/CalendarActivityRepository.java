package br.com.kproj.salesman.assistants.calendar.infrastructure.persistence.springdata;


import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.CalendarActivity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository("calendarActivityRepositoryCalendarModule")
public interface CalendarActivityRepository extends BaseRepositoryLegacy<CalendarActivity, Long> {


    @Query("SELECT ca FROM CalendarActivity AS ca join ca.period AS p" +
            " WHERE p.startDate >= :startDate AND p.endDate <= :endDate")
    List<CalendarActivity> findByRangeDates(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

}
