package br.com.kproj.salesman.assistants.calendar.infrastructure.persistence;


import br.com.kproj.salesman.assistants.calendar.domain.model.activity.Activity;
import br.com.kproj.salesman.assistants.calendar.domain.model.calendar.Calendar;
import br.com.kproj.salesman.assistants.calendar.domain.model.calendar.CalendarRepository;
import br.com.kproj.salesman.assistants.calendar.domain.model.user.User;
import br.com.kproj.salesman.assistants.calendar.infrastructure.persistence.springdata.CalendarEntityRepositorySpringdata;
import br.com.kproj.salesman.assistants.calendar.infrastructure.persistence.translate.CalendarActiityToActivity;
import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.CalendarEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Repository
public class CalendarRepositoryHibernate extends BaseRespositoryImpl<Calendar, CalendarEntity> implements CalendarRepository {

    private CalendarEntityRepositorySpringdata repository;

    private CalendarActiityToActivity activityConverter;

    @Autowired
    public CalendarRepositoryHibernate(CalendarEntityRepositorySpringdata repository, CalendarActiityToActivity activityConverter) {
        this.repository = repository;
        this.activityConverter = activityConverter;
    }

    @Override
    public Optional<Calendar> registerFor(User user) {
        CalendarEntity calendarEntity = new CalendarEntity();
        calendarEntity.setUser(new UserEntity(user.getId()));

        CalendarEntity entity = repository.save(calendarEntity);

        return Optional.of(getConverter().convert(entity));
    }

    @Override
    public BaseRepositoryLegacy<CalendarEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<CalendarEntity, Calendar> getConverter() {
        return ((calendarEntity, args) -> {
            Calendar calendar = new Calendar();
            calendar.setId(calendar.getId());
            calendar.setOwner(new User(calendarEntity.getUser().getId()));

            List<Activity> activities = calendarEntity.getActivities()
                    .stream().map(calendarActivity -> activityConverter.convert(calendarActivity))
                    .collect(Collectors.toList());

            calendar.setActivities(activities);

            return calendar;
        });
    }
}
