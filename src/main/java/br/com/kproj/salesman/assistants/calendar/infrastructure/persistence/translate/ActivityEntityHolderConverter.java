package br.com.kproj.salesman.assistants.calendar.infrastructure.persistence.translate;

import br.com.kproj.salesman.assistants.calendar.domain.model.activity.Activity;
import br.com.kproj.salesman.assistants.calendar.domain.model.calendar.Calendar;
import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.CalendarActivityEntity;
import org.springframework.stereotype.Component;

@Component
public class ActivityEntityHolderConverter {

    public void merge(Activity activity, CalendarActivityEntity entity) {
        activity.setId(entity.getId());
        activity.setDescription(entity.getDescription());
        activity.setLocation(entity.getLocation());
        activity.setTitle(entity.getTitle());

        activity.setAllDay(entity.getAllDay());
        activity.setEnd(entity.getEnd());
        activity.setStart(entity.getStart());

        if (entity.getCalendar() != null) {
            activity.setCalendar(new Calendar(entity.getCalendar().getId()));
        }
    }
}
