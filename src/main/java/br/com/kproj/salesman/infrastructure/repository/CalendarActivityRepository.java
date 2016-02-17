package br.com.kproj.salesman.infrastructure.repository;


import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.CalendarActivity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface CalendarActivityRepository extends BaseRepository<CalendarActivity, Long> {


    @Query("SELECT ca FROM CalendarActivity AS ca join ca.period AS p" +
            " WHERE p.startDate >= :startDate AND p.endDate <= :endDate")
    List<CalendarActivity> findByRangeDates(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

}
