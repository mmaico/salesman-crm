package br.com.kproj.salesman.assistants.calendar.application;

import br.com.kproj.salesman.assistants.calendar.infrastructure.CalendarRepository;
import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.builders.CalendarBuilder;
import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.Calendar;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.repository.UserRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CalendarApplicationImpl extends BaseModelServiceImpl<Calendar> implements CalendarApplication {

	@Autowired
	private CalendarRepository repository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public Calendar register(User user) {
        User userLoaded = userRepository.findOne(user.getId());
        Optional<Calendar> calendarLoaded = Optional.ofNullable(userLoaded.getCalendar());

        if (!calendarLoaded.isPresent()) {
            Calendar calendarNew = CalendarBuilder.create().withUser(user).build();
            Calendar calendarCreated = repository.save(calendarNew);
            userLoaded.setCalendar(calendarCreated);

            return calendarCreated;
        }

        return calendarLoaded.get();
    }

    public BaseRepository<Calendar, Long> getRepository() {
        return repository;
    }
}
