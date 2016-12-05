package br.com.kproj.salesman.infrastructure.entity.builders;

import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.CalendarActivityEntity;


public class CalendarActivityBuilder extends AbstractBuilder<CalendarActivityEntity> {

	private CalendarActivityEntity calendarActivityEntity = new CalendarActivityEntity();

    public CalendarActivityBuilder() {}

    public CalendarActivityBuilder(Long id) {
        calendarActivityEntity.setId(id);
    }




    public static CalendarActivityBuilder createCalendarActivity(Long id) {
        return new CalendarActivityBuilder(id);
    }
    public static CalendarActivityBuilder createCalendarActivity() {
        return new CalendarActivityBuilder();
    }

	public CalendarActivityEntity build() {
		return this.calendarActivityEntity;
	}


	
}
