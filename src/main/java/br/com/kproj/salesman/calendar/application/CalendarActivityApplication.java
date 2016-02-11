package br.com.kproj.salesman.calendar.application;


import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.calendar.CalendarActivity;
import br.com.kproj.salesman.infrastructure.service.ModelService;

import java.util.Date;
import java.util.List;

public interface CalendarActivityApplication extends ModelService<CalendarActivity> {

    CalendarActivity register(CalendarActivity activity, User user);

    List<CalendarActivity> findByRangeDate(Date startDate, Date endDate);
}
