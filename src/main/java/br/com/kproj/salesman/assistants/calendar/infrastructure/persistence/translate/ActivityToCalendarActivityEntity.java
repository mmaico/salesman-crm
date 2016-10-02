package br.com.kproj.salesman.assistants.calendar.infrastructure.persistence.translate;


import br.com.kproj.salesman.assistants.calendar.domain.model.activity.Activity;
import br.com.kproj.salesman.assistants.calendar.domain.model.activity.Period;
import br.com.kproj.salesman.assistants.calendar.domain.model.activity.Type;
import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.ActivityType;
import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.CalendarActivity;
import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.PeriodEntity;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.stereotype.Component;

@Component
public class ActivityToCalendarActivityEntity implements Converter<Activity, CalendarActivity> {

    private SmallConverter<Type, ActivityType> typeConverter;
    private SmallConverter<Period, PeriodEntity> periodConverter;

    {
         typeConverter = activityType -> {
            ActivityType type = new ActivityType();
            type.setId(activityType.getId());
            type.setName(activityType.getName());
            return type;
        };

        periodConverter = period -> {
            PeriodEntity periodEntity = new PeriodEntity();
            periodEntity.setId(periodEntity.getId());

            periodEntity.setId(period.getId());
            periodEntity.setAllDay(period.getAllDay());
            periodEntity.setEndDate(period.getEnd());
            periodEntity.setStartDate(period.getStart());

            return periodEntity;
        };
    }

    @Override
    public CalendarActivity convert(Activity activity, Object... args) {
        CalendarActivity calendarActivity = new CalendarActivity();
        calendarActivity.setId(activity.getId());
        calendarActivity.setDescription(activity.getDescription());
        calendarActivity.setLocation(activity.getLocation());
        calendarActivity.setTitle(activity.getTitle());


        calendarActivity.setType(typeConverter.convert(activity.getType()));
        calendarActivity.setPeriod(periodConverter.convert(activity.getPeriod()));

        return calendarActivity;
    }

    private interface SmallConverter<In, Out> {

        Out convert(In in);
    }

}
