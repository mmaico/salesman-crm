package br.com.kproj.salesman.assistants.calendar.infrastructure.persistence.translate;


import br.com.kproj.salesman.assistants.calendar.domain.model.activity.Activity;
import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.CalendarActivityEntity;
import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.activity_specialization.CalendarActivityType;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Component
public class CalendarActiityToActivity implements Converter<CalendarActivityEntity, Activity> {

    @Autowired
    private ActivityEntityHolderConverter baseConverter;

    @Resource(name="activitiesConverters")
    private Map<CalendarActivityType, Converter<? super Identifiable, ? extends Activity>> converters;

    @Override
    public Activity convert(CalendarActivityEntity activityEntity, Object... args) {
        Converter<? super Identifiable, ? extends Activity> converter = converters.get(activityEntity.getSpecialization());

        if (converter == null) {
            Activity activity = new Activity();
            baseConverter.merge(activity, activityEntity);
            return activity;
        } else {
            return  converter.convert(activityEntity);
        }

    }

}
