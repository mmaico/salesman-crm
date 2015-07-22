package br.com.kproj.salesman.register.domain.impl;

import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.register.domain.ProviderDomainService;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;

import static br.com.kproj.salesman.infrastructure.entity.enums.PersonProfilesEnum.COMPANY_PROVIDER;
import static br.com.kproj.salesman.infrastructure.entity.enums.PersonProfilesEnum.INDIVIDUAL_PROVIDER;

@Service
public class ProviderDomainServiceImpl implements ProviderDomainService {

	@Override
	public void verifyPreconditionToSave(Person person) {
		
		if(person.getProfile() == null ||
                !person.getProfile().equals(INDIVIDUAL_PROVIDER)
                || !person.getProfile().equals(COMPANY_PROVIDER)) {
			throw new ValidationException("provider.without.profile");
		}
		
	}

	
}
