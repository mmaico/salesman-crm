package br.com.kproj.salesman.infrastructure.entity.builders;

import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.calendar.Calendar;


public class CalendarBuilder extends AbstractBuilder<Calendar> {

	private Calendar calendar = new Calendar();

    public CalendarBuilder() {}

    public CalendarBuilder(Long id) {
        calendar.setId(id);
    }

    public CalendarBuilder withUser(User user) {
		this.calendar.setUser(user);
		return this;
	}


    public static CalendarBuilder create(Long id) {
        return new CalendarBuilder(id);
    }
    public static CalendarBuilder create() {
        return new CalendarBuilder();
    }

	public Calendar build() {
		return this.calendar;
	}


	
}
