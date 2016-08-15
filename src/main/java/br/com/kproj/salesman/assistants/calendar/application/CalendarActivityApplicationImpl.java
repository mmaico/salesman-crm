package br.com.kproj.salesman.assistants.calendar.application;

import br.com.kproj.salesman.assistants.calendar.application.dto.RangeDatesDTO;
import br.com.kproj.salesman.assistants.calendar.domain.CalendarActivityDomainService;
import br.com.kproj.salesman.infrastructure.helpers.NormalizeEntityRequest;
import br.com.kproj.salesman.infrastructure.repository.CalendarActivityRepository;
import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.Calendar;
import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.CalendarActivity;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceLegacyImpl;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalendarActivityApplicationImpl extends BaseModelServiceLegacyImpl<CalendarActivity> implements CalendarActivityApplication {

	@Autowired
	private CalendarActivityRepository repository;

    @Autowired
    private CalendarApplication calendarApplication;

    @Autowired
    private CalendarActivityDomainService activityService;

    @Autowired
    private NormalizeEntityRequest normalizeEntityRequest;


    public BaseRepositoryLegacy<CalendarActivity, Long> getRepository() {
        return repository;
    }

    @Override
    public CalendarActivity register(CalendarActivity activity, User user) {

        Calendar calendarLoaded = calendarApplication.register(user);
        if (activity.isNew()) {
            normalizeEntityRequest.doNestedReference(activity);
            activity.setCalendar(calendarLoaded);
        }
        normalizeEntityRequest.addFieldsToUpdate(activity);

        return super.save(activity, activityService);
    }

    @Override
    public List<CalendarActivity> findByRangeDate(RangeDatesDTO rangeDates) {

        if (rangeDates.hasValidRangeToSearch()) {
            throw new ValidationException(Sets.newHashSet("period.invalid.range.dates"));
        }

        return repository.findByRangeDates(rangeDates.getStartDate(), rangeDates.getEndDate());
    }
}
