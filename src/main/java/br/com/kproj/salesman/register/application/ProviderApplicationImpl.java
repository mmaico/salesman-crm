package br.com.kproj.salesman.register.application;

import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.entity.person.privider.Provider;
import br.com.kproj.salesman.infrastructure.helpers.Filter;
import br.com.kproj.salesman.infrastructure.helpers.FilterAggregator;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.repository.PersonRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import br.com.kproj.salesman.register.application.contract.ProviderApplication;
import br.com.kproj.salesman.register.domain.contract.ProviderDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static br.com.kproj.salesman.infrastructure.entity.enums.PersonProfilesEnum.COMPANY_PROVIDER;
import static br.com.kproj.salesman.infrastructure.entity.enums.PersonProfilesEnum.INDIVIDUAL_PROVIDER;
import static br.com.kproj.salesman.infrastructure.repository.predicates.PersonPredicate.findByFilters;
import static br.com.kproj.salesman.infrastructure.repository.predicates.PersonPredicate.orderByName;
import static com.google.common.collect.Lists.newArrayList;

@Service
public class ProviderApplicationImpl extends BaseModelServiceImpl<Person> implements ProviderApplication {

    private PersonRepository providerRepository;
    private ProviderDomainService domainService;

    @Autowired
    public ProviderApplicationImpl(PersonRepository clientRepository, ProviderDomainService domainService) {
        this.providerRepository = clientRepository;
        this.domainService = domainService;
    }

    @Override
    public Provider register(Provider provider) {
        return super.save((Person)provider, domainService);
    }
    
    @Override
    public Iterable<Person> findAll(Pageable pager) {
    	FilterAggregator aggregator = FilterAggregator.build()
    		.add(Filter.build("profiles", newArrayList(INDIVIDUAL_PROVIDER.get(), COMPANY_PROVIDER.get())));
    	
    	return providerRepository.findAll(findByFilters(aggregator), pager, orderByName());
    }

    public BaseRepository<Person, Long> getRepository() {
        return this.providerRepository;
    }

}
