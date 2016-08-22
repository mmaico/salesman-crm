package br.com.kproj.salesman.assistants.calendar.application;

import br.com.kproj.salesman.assistants.calendar.infrastructure.CalendarRepository;
import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.builders.CalendarBuilder;
import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.CalendarEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.UserEntityRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceLegacyImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CalendarApplicationImpl extends BaseModelServiceLegacyImpl<CalendarEntity> implements CalendarApplication {

	@Autowired
	private CalendarRepository repository;

    @Autowired
    private UserEntityRepository userEntityRepository;


    @Override
    public CalendarEntity register(UserEntity user) {
        UserEntity userLoaded = userEntityRepository.findOne(user.getId());
        Optional<CalendarEntity> calendarLoaded = Optional.ofNullable(userLoaded.getCalendarEntity());

        if (!calendarLoaded.isPresent()) {
            CalendarEntity calendarEntityNew = CalendarBuilder.create().withUser(user).build();
            CalendarEntity calendarEntityCreated = repository.save(calendarEntityNew);
            userLoaded.setCalendarEntity(calendarEntityCreated);

            return calendarEntityCreated;
        }

        return calendarLoaded.get();
    }

    public BaseRepositoryLegacy<CalendarEntity, Long> getRepository() {
        return repository;
    }
}
