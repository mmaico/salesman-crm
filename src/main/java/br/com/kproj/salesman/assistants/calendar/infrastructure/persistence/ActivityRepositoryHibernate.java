package br.com.kproj.salesman.assistants.calendar.infrastructure.persistence;

import br.com.kproj.salesman.assistants.calendar.domain.model.activity.Activity;
import br.com.kproj.salesman.assistants.calendar.domain.model.activity.ActivityRepository;
import br.com.kproj.salesman.assistants.calendar.domain.model.activity.NewActivity;
import br.com.kproj.salesman.assistants.calendar.domain.model.calendar.Calendar;
import br.com.kproj.salesman.assistants.calendar.infrastructure.persistence.springdata.CalendarActivityRepository;
import br.com.kproj.salesman.assistants.calendar.infrastructure.persistence.springdata.predicates.ActivitityFiltersDefinition;
import br.com.kproj.salesman.assistants.calendar.infrastructure.persistence.translate.CalendarActiityToActivity;
import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.CalendarActivityEntity;
import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.CalendarEntity;
import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.QCalendarActivityEntity;
import br.com.kproj.salesman.infrastructure.helpers.FilterAggregator;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.trex.clone.BusinessModelClone.from;

@Repository("activityRepositoryHibernateCalendarModule")
public class ActivityRepositoryHibernate extends BaseRespositoryImpl<Activity, CalendarActivityEntity> implements ActivityRepository {


    private CalendarActivityRepository repository;
    private CalendarActiityToActivity converter;
    private ActivitityFiltersDefinition activityFiltersDefinition;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public ActivityRepositoryHibernate(CalendarActivityRepository repository,
                                       CalendarActiityToActivity converter,
                                       ActivitityFiltersDefinition activityFiltersDefinition) {
        this.repository = repository;
        this.converter = converter;
        this.activityFiltersDefinition = activityFiltersDefinition;
    }


    @Override
    public Optional<Activity> register(NewActivity newActivity) {
        Activity activity = newActivity.getActivity();
        Calendar calendar = newActivity.getCalendar();

        CalendarActivityEntity calendarActivityEntity = from(activity).convertTo(CalendarActivityEntity.class);
        calendarActivityEntity.setCalendar(new CalendarEntity(calendar.getId()));

        CalendarActivityEntity activitySaved = repository.save(calendarActivityEntity);

        return Optional.of(getConverter().convert(activitySaved));
    }

    @Override
    public Iterable<Activity> findAll(Calendar calendar, FilterAggregator filters, Pageable pageable) {
        QCalendarActivityEntity calendarActivityEntity = QCalendarActivityEntity.calendarActivityEntity;

        JPQLQuery<CalendarActivityEntity> queryBase = new JPAQuery<CalendarActivityEntity>(em).from(calendarActivityEntity);
        BooleanBuilder booleanBuilder = activityFiltersDefinition.buildCriteria(filters, calendarActivityEntity);
        booleanBuilder.and(calendarActivityEntity.calendar.id.eq(calendar.getId()));

        JPQLQuery<CalendarActivityEntity> query = queryBase.where(booleanBuilder);
        long totalItems = query.fetchCount();

        List<CalendarActivityEntity> result = query.limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch();

        List<Activity> resulModel = result.stream().map(converter::convert)
                .collect(Collectors.toList());

        return new PageImpl<>(resulModel, pageable, totalItems);
    }

    @Override
    public BaseRepositoryLegacy<CalendarActivityEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<CalendarActivityEntity, Activity> getConverter() {
        return converter;
    }
}
