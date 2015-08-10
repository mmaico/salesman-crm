package br.com.kproj.salesman.infrastructure.configuration.initial;

import br.com.kproj.salesman.infrastructure.entity.enums.PersonProfilesEnum;
import br.com.kproj.salesman.infrastructure.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class PersonProfilesInit implements InitialProcess {

	@Autowired
	private ProfileRepository profileRepository;

	@Override
	@PostConstruct
	public void run() {

		long count = profileRepository.count();
		if (count < 1) {
			profileRepository.save(PersonProfilesEnum.getAll());
		}
	}

	
}
