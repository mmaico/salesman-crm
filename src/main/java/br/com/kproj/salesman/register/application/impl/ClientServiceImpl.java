package br.com.kproj.salesman.register.application.impl;

import static br.com.kproj.salesman.infrastructure.repository.predicates.PersonPredicate.findByFilters;
import static br.com.kproj.salesman.infrastructure.repository.predicates.PersonPredicate.orderByName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.kproj.salesman.infrastructure.entity.enums.PersonProfilesEnum;
import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.helpers.Filter;
import br.com.kproj.salesman.infrastructure.helpers.FilterAggregator;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.repository.PersonRepository;
import br.com.kproj.salesman.register.application.ClientService;
import br.com.kproj.salesman.register.domain.ClientDomainService;

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
    public Iterable<Person> findAll(Pageable pager) {
    	FilterAggregator aggregator = FilterAggregator.build()
    		.add(Filter.build(PersonProfilesEnum.COMPANY_CLIENT))
    		.add(Filter.build(PersonProfilesEnum.INDIVIDUAL_CLIENT));
    	
    	return clientRepository.findAll(findByFilters(aggregator), pager, orderByName());
    }

    @Override
    public BaseRepository<Person, Long> getRepository() {
        return this.clientRepository;
    }

}
