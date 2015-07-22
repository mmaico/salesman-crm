package br.com.kproj.salesman.register.application.impl;

import static br.com.kproj.salesman.infrastructure.repository.predicates.PersonPredicate.findByFilters;
import static br.com.kproj.salesman.infrastructure.repository.predicates.PersonPredicate.orderByName;
import br.com.kproj.salesman.infrastructure.entity.enums.PersonProfilesEnum;
import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.helpers.Filter;
import br.com.kproj.salesman.infrastructure.helpers.FilterAggregator;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.repository.PersonRepository;
import br.com.kproj.salesman.register.application.ProviderService;
import br.com.kproj.salesman.register.domain.ProviderDomainService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProviderServiceImpl extends BaseModelServiceImpl<Person> implements ProviderService {

    private PersonRepository providerRepository;
    private ProviderDomainService domainService;

    @Autowired
    public ProviderServiceImpl(PersonRepository clientRepository, ProviderDomainService domainService) {
        this.providerRepository = clientRepository;
        this.domainService = domainService;
    }

    @Override
    public Person register(Person provider) {
        domainService.verifyPreconditionToSave(provider);
        return super.save(provider);
    }
    
    @Override
    public Iterable<Person> findAll(Pageable pager) {
    	FilterAggregator aggregator = FilterAggregator.build()
    		.add(Filter.build(PersonProfilesEnum.INDIVIDUAL_PROVIDER))
    		.add(Filter.build(PersonProfilesEnum.COMPANY_PROVIDER));
    	
    	return providerRepository.findAll(findByFilters(aggregator), pager, orderByName());
    }

    @Override
    public BaseRepository<Person, Long> getRepository() {
        return this.providerRepository;
    }

}
