package br.com.kproj.salesman.register.domain;

import br.com.kproj.salesman.infrastructure.entity.person.privider.Provider;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.register.domain.contract.AddressDomainService;
import br.com.kproj.salesman.register.domain.contract.ProviderDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static br.com.kproj.salesman.infrastructure.entity.enums.PersonProfilesEnum.COMPANY_PROVIDER;
import static br.com.kproj.salesman.infrastructure.entity.enums.PersonProfilesEnum.INDIVIDUAL_PROVIDER;
import static com.google.common.collect.Sets.newHashSet;

@Service
public class ProviderDomainServiceImpl implements ProviderDomainService {

    @Autowired
    private AddressDomainService service;

	@Override
	public void verifyPreconditionToSave(Provider provider) {
		
		if(!INDIVIDUAL_PROVIDER.get().equals(provider.getProfile()) &&
				!COMPANY_PROVIDER.get().equals(provider.getProfile())) {
			throw new ValidationException(newHashSet("provider.without.profile"));
		}
        service.prepareToSave(provider.getAddresses());
	}

	
}
