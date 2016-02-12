package br.com.kproj.salesman.appointments.application;

import br.com.kproj.salesman.appointments.application.dto.RangeDatesDTO;
import br.com.kproj.salesman.appointments.domain.CalendarActivityDomainService;
import br.com.kproj.salesman.infrastructure.repository.CalendarActivityRepository;
import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.calendar.Calendar;
import br.com.kproj.salesman.infrastructure.entity.calendar.CalendarActivity;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<CalendarActivity> findByRangeDate(RangeDatesDTO rangeDates) {

        if (rangeDates.hasValidRangeToSearch()) {
            throw new ValidationException(Sets.newHashSet("period.invalid.range.dates"));
        }

        return repository.findByRangeDates(rangeDates.getStartDate(), rangeDates.getEndDate());
    }
}
