package br.com.kproj.salesman.assistants.calendar.activities.specialization.infrastructure.persistence;


import br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.activity.Activity;
import br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.activity.ActivityRepository;
import br.com.kproj.salesman.assistants.calendar.activities.specialization.infrastructure.persistence.springdata.CalendarActivityRepositorySpringData;
import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.CalendarActivityEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("activityRepositoryHibernateActivitiesCalendarModule")
public class ActivityRepositoryHibernate extends BaseRespositoryImpl<Activity, CalendarActivityEntity> implements ActivityRepository {


    private CalendarActivityRepositorySpringData repository;

    @Autowired
    public ActivityRepositoryHibernate(CalendarActivityRepositorySpringData repository) {
        this.repository = repository;
    }

    @Override
    public Boolean hasSpecialization(Activity activity) {
        CalendarActivityEntity entity = repository.findOne(activity.getId());
        return entity.getRepresent() != null;
    }

    @Override
    public BaseRepositoryLegacy<CalendarActivityEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<CalendarActivityEntity, Activity> getConverter() {
        return ((activityEntity, args) -> new Activity(activityEntity.getId()));
    }

}
