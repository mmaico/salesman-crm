package br.com.kproj.salesman.assistants.calendar.infrastructure.persistence.translate;


import br.com.kproj.salesman.assistants.calendar.domain.model.activity.Activity;
import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.CalendarActivityEntity;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.stereotype.Component;

@Component
public class ActivityToCalendarActivityEntity implements Converter<Activity, CalendarActivityEntity> {


    @Override
    public CalendarActivityEntity convert(Activity activity, Object... args) {
        CalendarActivityEntity calendarActivityEntity = new CalendarActivityEntity();
        calendarActivityEntity.setId(activity.getId());
        calendarActivityEntity.setDescription(activity.getDescription());
        calendarActivityEntity.setLocation(activity.getLocation());
        calendarActivityEntity.setTitle(activity.getTitle());

        //calendarActivityEntity.setType(typeConverter.convert(activity.getType()));
        //calendarActivityEntity.setPeriod(periodConverter.convert(activity.getPeriod()));

        return calendarActivityEntity;
    }


}
