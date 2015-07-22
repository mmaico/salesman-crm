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
		
		if(person.getProfile() == null ||
                !person.getProfile().equals(INDIVIDUAL_CLIENT)
                || !person.getProfile().equals(COMPANY_CLIENT)) {
			throw new ValidationException("client.without.profile");
		}
		
	}

	
}
