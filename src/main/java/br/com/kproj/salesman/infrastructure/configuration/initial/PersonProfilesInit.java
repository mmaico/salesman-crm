package br.com.kproj.salesman.infrastructure.configuration.initial;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.kproj.salesman.infrastructure.entity.enums.PersonProfilesEnum;
import br.com.kproj.salesman.infrastructure.repository.ProfileRepository;

@Component
public class PersonProfilesInit implements InitialProcess {

	@Autowired
	private ProfileRepository profileRepository;
	
	@Override
	@PostConstruct
	public void run() {
		
		long count = profileRepository.count();
		if (count < 1) {
			PersonProfilesEnum.getAll().forEach(e -> profileRepository.save(e));
		}
	}

	
}
