package br.com.kproj.salesman.assistants.calendar.activities.specialization.infrastructure.persistence;


import br.com.kproj.salesman.assistants.calendar.activities.activity.infrastructure.persistence.springdata.CalendarActivityRepository;
import br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.activity.ActivitySaleable;
import br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.activity.ActivitySaleableRepository;
import br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.saleable.Saleable;
import br.com.kproj.salesman.assistants.calendar.activities.specialization.infrastructure.persistence.springdata.CalendarActivitySaleableRepositorySpringData;
import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.CalendarActivityEntity;
import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.activity_specialization.CalendarActivitySaleableEntity;
import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.activity_specialization.CalendarActivityType;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("activitySaleableRepositoryHibernateCalendarModule")
public class ActivitySaleableRepositoryHibernate extends BaseRespositoryImpl<ActivitySaleable, CalendarActivitySaleableEntity> implements ActivitySaleableRepository {


    private CalendarActivitySaleableRepositorySpringData repository;
    private CalendarActivityRepository activityRepository;

    @Autowired
    public ActivitySaleableRepositoryHibernate(CalendarActivitySaleableRepositorySpringData repository,
                                               CalendarActivityRepository activityRepository) {
        this.repository = repository;
        this.activityRepository = activityRepository;
    }

    @Override
    public Optional<ActivitySaleable> makeSpecialization(ActivitySaleable activityNegotiation) {
        CalendarActivityEntity activityEntity = activityRepository.findOne(activityNegotiation.getId());

        CalendarActivitySaleableEntity businessProposalEntity = new CalendarActivitySaleableEntity();
        businessProposalEntity.setId(activityNegotiation.getId());
        businessProposalEntity.setSaleable(new SaleableUnitEntity(activityNegotiation.getSaleable().getId()));
        businessProposalEntity.setActivity(activityEntity);
        activityEntity.setRepresent(CalendarActivityType.SALEABLE);
        CalendarActivitySaleableEntity activitySaleableEntitySaved = repository.save(businessProposalEntity);

        return Optional.of(getConverter().convert(activitySaleableEntitySaved));
    }

    @Override
    public BaseRepositoryLegacy<CalendarActivitySaleableEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<CalendarActivitySaleableEntity, ActivitySaleable> getConverter() {
        return ((activityEntity, args) -> {

            ActivitySaleable activitySaleable = new ActivitySaleable();
            activitySaleable.setId(activityEntity.getId());
            activitySaleable.setSaleable(new Saleable(activityEntity.getSaleable().getId()));

            return activitySaleable;
        });
    }
}
