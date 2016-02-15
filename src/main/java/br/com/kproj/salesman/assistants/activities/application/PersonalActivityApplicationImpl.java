package br.com.kproj.salesman.assistants.activities.application;

import br.com.kproj.salesman.assistants.activities.infrastructure.PersonalAcvitityRepository;
import br.com.kproj.salesman.assistants.calendar.application.CalendarApplication;
import br.com.kproj.salesman.assistants.calendar.infrastructure.CalendarRepository;
import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.activities.PersonalActivity;
import br.com.kproj.salesman.infrastructure.entity.builders.CalendarBuilder;
import br.com.kproj.salesman.infrastructure.entity.calendar.Calendar;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.repository.UserRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonalActivityApplicationImpl extends BaseModelServiceImpl<PersonalActivity> implements PersonalAcvitityApplication {

	@Autowired
	private PersonalAcvitityRepository repository;




    @Override
    public PersonalActivity register(PersonalActivity personalActivity) {

        return null;
    }

    public BaseRepository<PersonalActivity, Long> getRepository() {
        return repository;
    }
}
