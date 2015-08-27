package br.com.kproj.salesman.register.domain.impl;

import br.com.kproj.salesman.infrastructure.entity.person.privider.Provider;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.register.domain.ProviderDomainService;
import org.springframework.stereotype.Service;

import static br.com.kproj.salesman.infrastructure.entity.enums.PersonProfilesEnum.COMPANY_PROVIDER;
import static br.com.kproj.salesman.infrastructure.entity.enums.PersonProfilesEnum.INDIVIDUAL_PROVIDER;
import static com.google.common.collect.Sets.newHashSet;

@Service
public class ProviderDomainServiceImpl implements ProviderDomainService {

	@Override
	public void verifyPreconditionToSave(Provider person) {
		
		if(!INDIVIDUAL_PROVIDER.get().equals(person.getProfile()) &&
				!COMPANY_PROVIDER.get().equals(person.getProfile())) {
			throw new ValidationException(newHashSet("provider.without.profile"));
		}
		
	}

	
}
