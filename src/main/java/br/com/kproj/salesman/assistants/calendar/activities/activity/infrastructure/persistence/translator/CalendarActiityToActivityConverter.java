package br.com.kproj.salesman.assistants.calendar.activities.activity.infrastructure.persistence.translator;


import br.com.kproj.salesman.assistants.calendar.activities.activity.domain.model.activity.Activity;
import br.com.kproj.salesman.assistants.calendar.activities.activity.domain.model.activity.Represent;
import br.com.kproj.salesman.assistants.calendar.activities.activity.domain.model.calendar.Calendar;
import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.CalendarActivityEntity;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.stereotype.Component;

@Component
public class CalendarActiityToActivityConverter implements Converter<CalendarActivityEntity, Activity> {



    @Override
    public Activity convert(CalendarActivityEntity activityEntity, Object... args) {
        Activity activity = new Activity();

        activity.setId(activityEntity.getId());
        activity.setDescription(activityEntity.getDescription());
        activity.setLocation(activityEntity.getLocation());
        activity.setTitle(activityEntity.getTitle());

        activity.setAllDay(activityEntity.getAllDay());
        activity.setEnd(activityEntity.getEnd());
        activity.setStart(activityEntity.getStart());

        if (activityEntity.getRepresent() != null) {
            activity.setRepresent(Represent.valueOf(activityEntity.getRepresent().name()));
        }

        if (activityEntity.getCalendar() != null) {
            activity.setCalendar(new Calendar(activityEntity.getCalendar().getId()));
        }

        return activity;
    }

}
