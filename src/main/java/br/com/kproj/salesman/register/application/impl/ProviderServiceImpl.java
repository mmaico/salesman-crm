package br.com.kproj.salesman.register.application.impl;

import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.entity.person.privider.Provider;
import br.com.kproj.salesman.infrastructure.helpers.Filter;
import br.com.kproj.salesman.infrastructure.helpers.FilterAggregator;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.repository.PersonRepository;
import br.com.kproj.salesman.infrastructure.service.impl.BaseModelServiceImpl;
import br.com.kproj.salesman.register.application.ProviderService;
import br.com.kproj.salesman.register.domain.ProviderDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static br.com.kproj.salesman.infrastructure.entity.enums.PersonProfilesEnum.COMPANY_PROVIDER;
import static br.com.kproj.salesman.infrastructure.entity.enums.PersonProfilesEnum.INDIVIDUAL_PROVIDER;
import static br.com.kproj.salesman.infrastructure.repository.predicates.PersonPredicate.findByFilters;
import static br.com.kproj.salesman.infrastructure.repository.predicates.PersonPredicate.orderByName;
import static com.google.common.collect.Lists.newArrayList;

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
    public Provider register(Provider provider) {
        domainService.verifyPreconditionToSave(provider);
        return super.save((Person)provider);
    }
    
    @Override
    public Iterable<Person> findAll(Pageable pager) {
    	FilterAggregator aggregator = FilterAggregator.build()
    		.add(Filter.build("profiles", newArrayList(INDIVIDUAL_PROVIDER.get(), COMPANY_PROVIDER.get())));
    	
    	return providerRepository.findAll(findByFilters(aggregator), pager, orderByName());
    }

    @Override
    public BaseRepository<Person, Long> getRepository() {
        return this.providerRepository;
    }

}
