package br.com.kproj.salesman.assistants.calendar.infrastructure.persistence.translate;


import br.com.kproj.salesman.assistants.calendar.domain.model.activity.Activity;
import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.CalendarActivityEntity;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.stereotype.Component;

@Component
public class CalendarActiityToActivity implements Converter<CalendarActivityEntity, Activity> {



    @Override
    public Activity convert(CalendarActivityEntity calendarActivityEntity, Object... args) {
        Activity activity = new Activity();
        activity.setId(calendarActivityEntity.getId());
        activity.setDescription(calendarActivityEntity.getDescription());
        activity.setLocation(calendarActivityEntity.getLocation());
        activity.setTitle(calendarActivityEntity.getTitle());

        activity.setAllDay(calendarActivityEntity.getAllDay());
        activity.setEnd(calendarActivityEntity.getEndDate());
        activity.setStart(calendarActivityEntity.getStartDate());


        return activity;
    }

}
