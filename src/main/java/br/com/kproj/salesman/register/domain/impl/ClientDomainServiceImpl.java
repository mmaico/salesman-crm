package br.com.kproj.salesman.register.domain.impl;

import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.register.domain.ClientDomainService;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;

import static br.com.kproj.salesman.infrastructure.entity.enums.PersonProfilesEnum.COMPANY_CLIENT;
import static br.com.kproj.salesman.infrastructure.entity.enums.PersonProfilesEnum.INDIVIDUAL_CLIENT;

@Service
public class ClientDomainServiceImpl implements ClientDomainService {

	@Override
	public void verifyPreconditionToSave(Person person) {

		if(!INDIVIDUAL_CLIENT.get().equals(person.getProfile()) &&
				!COMPANY_CLIENT.get().equals(person.getProfile())) {
			throw new ValidationException("client.without.profile");
		}
		
	}

	
}
