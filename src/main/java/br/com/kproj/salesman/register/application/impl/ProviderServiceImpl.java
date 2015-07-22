package br.com.kproj.salesman.register.application.impl;

import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.repository.PersonRepository;
import br.com.kproj.salesman.register.application.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProviderServiceImpl extends BaseModelServiceImpl<Person> implements ProviderService {

    private PersonRepository providerRepository;

    @Autowired
    public ProviderServiceImpl(PersonRepository clientRepository) {
        this.providerRepository = clientRepository;
    }

    @Override
    public Person register(Person provider) {
        return super.save(provider);
    }

    @Override
    public BaseRepository<Person, Long> getRepository() {
        return this.providerRepository;
    }

}
