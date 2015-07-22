package br.com.kproj.salesman.register.domain;

import org.springframework.stereotype.Service;

import br.com.kproj.salesman.infrastructure.entity.person.Person;

@Service
public class ProviderDomainServiceImpl implements ProviderDomainService {

	@Override
	public void verifyPreconditionToSave(Person person) {
		
		if(person.getProfile() == null || person.getProfile().equals(o)) {
			
		}
		
	}

	
}
