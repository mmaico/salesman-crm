package br.com.kproj.salesman.assistants.calendar.calendar.infrastructure.persistence;


import br.com.kproj.salesman.assistants.calendar.domain.model.calendar.Calendar;
import br.com.kproj.salesman.assistants.calendar.domain.model.calendar.CalendarRepository;
import br.com.kproj.salesman.assistants.calendar.domain.model.user.User;
import br.com.kproj.salesman.assistants.calendar.infrastructure.persistence.springdata.CalendarEntityRepositorySpringdata;
import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.CalendarEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public class CalendarRepositoryHibernate extends BaseRespositoryImpl<Calendar, CalendarEntity> implements CalendarRepository {

    private CalendarEntityRepositorySpringdata repository;

    @Autowired
    public CalendarRepositoryHibernate(CalendarEntityRepositorySpringdata repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Calendar> registerFor(User user) {
        CalendarEntity calendarEntity = new CalendarEntity();
        calendarEntity.setUser(new UserEntity(user.getId()));

        CalendarEntity entity = repository.save(calendarEntity);

        return Optional.of(getConverter().convert(entity));
    }

    @Override
    public Boolean hasFor(User user) {
        Optional<CalendarEntity> calendarEntity = repository.hasFor(user.getId());
        return calendarEntity.isPresent();
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
            calendar.setOwner(new User(calendarEntity.getUser().getId()));

            return calendar;
        });
    }
}
