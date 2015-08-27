package br.com.kproj.salesman.register.domain.impl;

import br.com.kproj.salesman.infrastructure.entity.person.client.Client;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.register.domain.ClientDomainService;
import org.springframework.stereotype.Service;

import static br.com.kproj.salesman.infrastructure.entity.enums.PersonProfilesEnum.COMPANY_CLIENT;
import static br.com.kproj.salesman.infrastructure.entity.enums.PersonProfilesEnum.INDIVIDUAL_CLIENT;
import static com.google.common.collect.Sets.newHashSet;

@Service
public class ClientDomainServiceImpl implements ClientDomainService {

	@Override
	public void verifyPreconditionToSave(Client person) {

		if(!INDIVIDUAL_CLIENT.get().equals(person.getProfile()) &&
				!COMPANY_CLIENT.get().equals(person.getProfile())) {
			throw new ValidationException(newHashSet("client.without.profile"));
		}
        
	}

	
}
