package br.com.kproj.salesman.assistants.calendar.application;


import br.com.kproj.salesman.assistants.calendar.application.dto.RangeDatesDTO;
import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.CalendarActivity;
import br.com.kproj.salesman.infrastructure.service.ModelLegacyService;

import java.util.List;

public interface CalendarActivityApplication extends ModelLegacyService<CalendarActivity> {

    CalendarActivity register(CalendarActivity activity, User user);

    List<CalendarActivity> findByRangeDate(RangeDatesDTO rangeDates);
}
