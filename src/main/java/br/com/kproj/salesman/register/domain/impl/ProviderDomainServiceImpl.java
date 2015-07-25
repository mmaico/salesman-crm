package br.com.kproj.salesman.register.domain.impl;

import static br.com.kproj.salesman.infrastructure.entity.enums.PersonProfilesEnum.COMPANY_PROVIDER;
import static br.com.kproj.salesman.infrastructure.entity.enums.PersonProfilesEnum.INDIVIDUAL_PROVIDER;

import javax.validation.ValidationException;

import org.springframework.stereotype.Service;

import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.register.domain.ProviderDomainService;

@Service
public class ProviderDomainServiceImpl implements ProviderDomainService {

	@Override
	public void verifyPreconditionToSave(Person person) {
		
		if(!INDIVIDUAL_PROVIDER.get().equals(person.getProfile()) &&
				!COMPANY_PROVIDER.get().equals(person.getProfile())) {
			throw new ValidationException("provider.without.profile");
		}
		
	}

	
}
