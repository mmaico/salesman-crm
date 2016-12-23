package br.com.kproj.salesman.assistants.calendar.infrastructure.persistence.translate.specializations;


import br.com.kproj.salesman.assistants.calendar.domain.model.activity.specialization.ActivitySaleable;
import br.com.kproj.salesman.assistants.calendar.domain.model.activity.specialization.Represent;
import br.com.kproj.salesman.assistants.calendar.domain.model.relations.Saleable;
import br.com.kproj.salesman.assistants.calendar.infrastructure.persistence.springdata.CalendarActivitySaleableRepository;
import br.com.kproj.salesman.assistants.calendar.infrastructure.persistence.translate.ActivityEntityHolderConverter;
import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.CalendarActivityEntity;
import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.activity_specialization.CalendarActivitySaleableEntity;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CalendarActiitySaleableToActivityConverter implements Converter<CalendarActivityEntity, ActivitySaleable> {


    private CalendarActivitySaleableRepository repository;
    private ActivityEntityHolderConverter converter;


    @Autowired
    public CalendarActiitySaleableToActivityConverter(CalendarActivitySaleableRepository repository, ActivityEntityHolderConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }


    @Override
    public ActivitySaleable convert(CalendarActivityEntity activityEntity, Object... args) {
        CalendarActivitySaleableEntity entity = repository.findOne(activityEntity.getId());

        ActivitySaleable activity = new ActivitySaleable();
        activity.setRepresent(Represent.SALEABLE);
        converter.merge(activity, activityEntity);
        activity.setSaleable(new Saleable(entity.getSaleable().getId()));

        return activity;
    }

}
