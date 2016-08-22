package br.com.kproj.salesman.infrastructure.entity.builders;

import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.CalendarEntity;


public class CalendarBuilder extends AbstractBuilder<CalendarEntity> {

	private CalendarEntity calendarEntity = new CalendarEntity();

    public CalendarBuilder() {}

    public CalendarBuilder(Long id) {
        calendarEntity.setId(id);
    }

    public CalendarBuilder withUser(UserEntity user) {
		this.calendarEntity.setUser(user);
		return this;
	}


    public static CalendarBuilder create(Long id) {
        return new CalendarBuilder(id);
    }
    public static CalendarBuilder create() {
        return new CalendarBuilder();
    }

	public CalendarEntity build() {
		return this.calendarEntity;
	}


	
}
