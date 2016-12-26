package br.com.kproj.salesman.assistants.calendar.activities.activity.infrastructure.persistence;




import br.com.kproj.salesman.assistants.calendar.activities.activity.domain.model.calendar.Calendar;
import br.com.kproj.salesman.assistants.calendar.activities.activity.domain.model.calendar.CalendarRepository;

import br.com.kproj.salesman.assistants.calendar.activities.activity.infrastructure.persistence.springdata.CalendarEntityRepositorySpringdata;
import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.CalendarEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository("calendarRepositoryHibernateActvity")
public class CalendarRepositoryHibernate extends BaseRespositoryImpl<Calendar, CalendarEntity> implements CalendarRepository {

    private CalendarEntityRepositorySpringdata repository;

    @Autowired
    public CalendarRepositoryHibernate(CalendarEntityRepositorySpringdata repository) {
        this.repository = repository;
    }

    @Override
    public BaseRepositoryLegacy<CalendarEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<CalendarEntity, Calendar> getConverter() {
        return ((calendarEntity, args) -> {
            Calendar calendar = new Calendar();
            calendar.setId(calendarEntity.getId());
            return calendar;
        });
    }
}
