package br.com.kproj.salesman.assistants.calendar.application;


import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.CalendarEntity;
import br.com.kproj.salesman.infrastructure.service.ModelLegacyService;

public interface CalendarApplication extends ModelLegacyService<CalendarEntity> {

    CalendarEntity register(UserEntity user);
}
