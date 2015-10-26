package br.com.kproj.salesman.register.domain;

import br.com.kproj.salesman.infrastructure.entity.person.client.Client;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.register.domain.contract.AddressDomainService;
import br.com.kproj.salesman.register.domain.contract.ClientDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static br.com.kproj.salesman.infrastructure.entity.enums.PersonProfilesEnum.COMPANY_CLIENT;
import static br.com.kproj.salesman.infrastructure.entity.enums.PersonProfilesEnum.INDIVIDUAL_CLIENT;
import static com.google.common.collect.Sets.newHashSet;

@Service
public class ClientDomainServiceImpl implements ClientDomainService {

    @Autowired
    private AddressDomainService service;

	@Override
	public void verifyPreconditionToSave(Client client) {

		if(!INDIVIDUAL_CLIENT.get().equals(client.getProfile()) &&
				!COMPANY_CLIENT.get().equals(client.getProfile())) {
			throw new ValidationException(newHashSet("client.without.profile"));
		}
        service.prepareToSave(client.getAddresses());
	}

	
}
