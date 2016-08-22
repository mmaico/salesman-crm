package br.com.kproj.salesman.assistants.calendar.application;


import br.com.kproj.salesman.assistants.calendar.application.dto.RangeDatesDTO;
import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.CalendarActivity;
import br.com.kproj.salesman.infrastructure.service.ModelLegacyService;

import java.util.List;

public interface CalendarActivityApplication extends ModelLegacyService<CalendarActivity> {

    CalendarActivity register(CalendarActivity activity, UserEntity user);

    List<CalendarActivity> findByRangeDate(RangeDatesDTO rangeDates);
}
