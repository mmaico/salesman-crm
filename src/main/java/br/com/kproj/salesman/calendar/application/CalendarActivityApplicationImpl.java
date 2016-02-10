package br.com.kproj.salesman.calendar.application;

import br.com.kproj.salesman.calendar.infrastructure.CalendarActivityRepository;
import br.com.kproj.salesman.calendar.infrastructure.CalendarRepository;
import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.calendar.Calendar;
import br.com.kproj.salesman.infrastructure.entity.calendar.CalendarActivity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalendarActivityApplicationImpl extends BaseModelServiceImpl<CalendarActivity> implements CalendarActivityApplication {

	@Autowired
	private CalendarActivityRepository repository;

    @Autowired
    private CalendarApplication calendarApplication;




    public BaseRepository<CalendarActivity, Long> getRepository() {
        return repository;
    }

    @Override
    public CalendarActivity register(CalendarActivity activity, User user) {

        return null;
    }
}
