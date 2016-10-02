package br.com.kproj.salesman.assistants.calendar.infrastructure.persistence;

import br.com.kproj.salesman.assistants.calendar.domain.model.activity.Activity;
import br.com.kproj.salesman.assistants.calendar.domain.model.activity.ActivityRepository;
import br.com.kproj.salesman.assistants.calendar.domain.model.activity.NewActivity;
import br.com.kproj.salesman.assistants.calendar.domain.model.activity.RangeDate;
import br.com.kproj.salesman.assistants.calendar.domain.model.calendar.Calendar;
import br.com.kproj.salesman.assistants.calendar.infrastructure.persistence.springdata.CalendarActivityRepository;
import br.com.kproj.salesman.assistants.calendar.infrastructure.persistence.translate.ActivityToCalendarActivityEntity;
import br.com.kproj.salesman.assistants.calendar.infrastructure.persistence.translate.CalendarActiityToActivity;
import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.CalendarActivity;
import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.CalendarEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository("activityRepositoryHibernateCalendarModule")
public class ActivityRepositoryHibernate extends BaseRespositoryImpl<Activity, CalendarActivity> implements ActivityRepository {


    private CalendarActivityRepository repository;
    private CalendarActiityToActivity converter;
    private ActivityToCalendarActivityEntity entityConverter;

    @Autowired
    public ActivityRepositoryHibernate(CalendarActivityRepository repository, CalendarActiityToActivity converter,
                                       ActivityToCalendarActivityEntity entityConverter) {
        this.repository = repository;
        this.converter = converter;
        this.entityConverter = entityConverter;
    }

    @Override
    public List<Activity> findAll(RangeDate range) {
        List<CalendarActivity> activities = repository.findByRangeDates(range.getStart(), range.getEnd());

        return activities.stream().map(converter::convert).collect(Collectors.toList());
    }

    @Override
    public Optional<Activity> register(NewActivity newActivity) {
        Activity activity = newActivity.getActivity();
        Calendar calendar = newActivity.getCalendar();

        CalendarActivity calendarActivity = entityConverter.convert(activity);
        calendarActivity.setCalendar(new CalendarEntity(calendar.getId()));

        CalendarActivity activitySaved = repository.save(calendarActivity);

        return Optional.of(getConverter().convert(activitySaved));
    }

    @Override
    public BaseRepositoryLegacy<CalendarActivity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<CalendarActivity, Activity> getConverter() {
        return converter;
    }
}
