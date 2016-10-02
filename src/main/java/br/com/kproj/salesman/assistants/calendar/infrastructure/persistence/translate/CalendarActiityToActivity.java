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
public class CalendarActiityToActivity implements Converter<CalendarActivity, Activity> {

    private SmallConverter<ActivityType, Type> typeConverter;
    private SmallConverter<PeriodEntity, Period> periodConverter;

    {
         typeConverter = activityType -> {
            Type type = new Type();
            type.setId(activityType.getId());
            type.setName(activityType.getName());
            return type;
        };

        periodConverter = periodEntity -> {
            Period period = new Period();
            period.setId(periodEntity.getId());
            period.setAllDay(periodEntity.getAllDay());
            period.setEnd(periodEntity.getEndDate());
            period.setStart(periodEntity.getStartDate());
            return period;
        };
    }

    @Override
    public Activity convert(CalendarActivity calendarActivity, Object... args) {
        Activity activity = new Activity();
        activity.setId(calendarActivity.getId());
        activity.setDescription(calendarActivity.getDescription());
        activity.setLocation(calendarActivity.getLocation());
        activity.setTitle(calendarActivity.getTitle());

        activity.setType(typeConverter.convert(calendarActivity.getType()));
        activity.setPeriod(periodConverter.convert(calendarActivity.getPeriod()));

        return activity;
    }

    private interface SmallConverter<In, Out> {

        Out convert(In in);
    }

}
