package br.com.kproj.salesman.calendar.application;

import br.com.kproj.salesman.calendar.domain.CalendarActivityDomainService;
import br.com.kproj.salesman.calendar.infrastructure.CalendarActivityRepository;
import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.calendar.Calendar;
import br.com.kproj.salesman.infrastructure.entity.calendar.CalendarActivity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CalendarActivityApplicationImpl extends BaseModelServiceImpl<CalendarActivity> implements CalendarActivityApplication {

	@Autowired
	private CalendarActivityRepository repository;

    @Autowired
    private CalendarApplication calendarApplication;

    @Autowired
    private CalendarActivityDomainService activityService;


    public BaseRepository<CalendarActivity, Long> getRepository() {
        return repository;
    }

    @Override
    public CalendarActivity register(CalendarActivity activity, User user) {

        Calendar calendarLoaded = calendarApplication.register(user);
        if (activity.isNew()) {
            activity.setCalendar(calendarLoaded);
        }
        activityService.hasValidDataToShowOnCalendar(activity);

        return super.save(activity);
    }

    @Override
    public List<CalendarActivity> findByRangeDate(Date startDate, Date endDate) {

        return null;
    }
}
