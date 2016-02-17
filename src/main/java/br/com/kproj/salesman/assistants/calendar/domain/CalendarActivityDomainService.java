package br.com.kproj.salesman.assistants.calendar.domain;

import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.CalendarActivity;

public interface CalendarActivityDomainService {

    void hasValidDataToShowOnCalendar(CalendarActivity activity);
}
