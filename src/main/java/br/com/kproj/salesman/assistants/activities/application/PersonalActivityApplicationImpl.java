package br.com.kproj.salesman.assistants.activities.application;

import br.com.kproj.salesman.assistants.activities.infrastructure.PersonalAcvitityRepository;
import br.com.kproj.salesman.infrastructure.entity.activities.PersonalActivity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
