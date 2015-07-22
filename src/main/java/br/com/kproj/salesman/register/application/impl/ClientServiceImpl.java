package br.com.kproj.salesman.register.application.impl;

import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.repository.PersonRepository;
import br.com.kproj.salesman.register.application.ClientService;
import br.com.kproj.salesman.register.domain.ClientDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl extends BaseModelServiceImpl<Person> implements ClientService {

    private PersonRepository clientRepository;

    private ClientDomainService domainService;

    @Autowired
    public ClientServiceImpl(PersonRepository clientRepository, ClientDomainService domainService) {
        this.clientRepository = clientRepository;
        this.domainService = domainService;
    }

    @Override
    public Person register(Person client) {
        domainService.verifyPreconditionToSave(client);
        return super.save(client);
    }

    @Override
    public BaseRepository getRepository() {
        return this.clientRepository;
    }

}
