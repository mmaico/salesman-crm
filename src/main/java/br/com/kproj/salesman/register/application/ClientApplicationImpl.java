package br.com.kproj.salesman.register.application;

import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.entity.person.client.Client;
import br.com.kproj.salesman.infrastructure.helpers.Filter;
import br.com.kproj.salesman.infrastructure.helpers.FilterAggregator;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.repository.PersonRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import br.com.kproj.salesman.register.application.contract.ClientApplication;
import br.com.kproj.salesman.register.domain.contract.ClientDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static br.com.kproj.salesman.infrastructure.entity.enums.PersonProfilesEnum.COMPANY_CLIENT;
import static br.com.kproj.salesman.infrastructure.entity.enums.PersonProfilesEnum.INDIVIDUAL_CLIENT;
import static br.com.kproj.salesman.infrastructure.repository.predicates.PersonPredicate.findByFilters;
import static br.com.kproj.salesman.infrastructure.repository.predicates.PersonPredicate.orderByName;
import static com.google.common.collect.Lists.newArrayList;

@Service
public class ClientApplicationImpl extends BaseModelServiceImpl<Person> implements ClientApplication {

    private PersonRepository clientRepository;

    private ClientDomainService domainService;

    @Autowired
    public ClientApplicationImpl(PersonRepository clientRepository, ClientDomainService domainService) {
        this.clientRepository = clientRepository;
        this.domainService = domainService;
    }

    @Override
    public Client register(Client client) {

        return super.save(client.to(), domainService);
    }
    
    @Override
    public Iterable<Person> findAll(Pageable pager) {
    	FilterAggregator aggregator = FilterAggregator.build()
    		.add(Filter.build("profiles", newArrayList(COMPANY_CLIENT.get(), INDIVIDUAL_CLIENT.get())));
    	
    	return clientRepository.findAll(findByFilters(aggregator), pager, orderByName());
    }

    @Override
    public BaseRepository<Person, Long> getRepository() {
        return this.clientRepository;
    }

}
