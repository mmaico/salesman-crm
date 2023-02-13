package br.com.kproj.salesman.accounts.customers.infrastructure.persistence;

import br.com.kproj.salesman.accounts.customers.domain.model.customer.Customer;
import br.com.kproj.salesman.accounts.customers.domain.model.customer.CustomerRepository;
import br.com.kproj.salesman.accounts.customers.infrastructure.persistence.springdata.CustomerEntityRepositorySpringData;
import br.com.kproj.salesman.accounts.customers.infrastructure.persistence.translate.CustomerEntityToAccountConverter;
import br.com.kproj.salesman.infrastructure.entity.accounts.CustomerEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.github.mmaico.clone.BusinessModelClone.from;

@Repository("customerRepositoryHibernateAccountModule")
public class CustomerRepositoryHibernate extends BaseRespositoryImpl<Customer, CustomerEntity> implements CustomerRepository {

    private CustomerEntityRepositorySpringData repository;

    private CustomerEntityToAccountConverter converter;

    @Autowired
    public CustomerRepositoryHibernate(CustomerEntityRepositorySpringData repository, CustomerEntityToAccountConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public Optional<Customer> save(Customer entity) {
        CustomerEntity customerEntity = from(entity).convertTo(CustomerEntity.class);

        return Optional.of(getConverter().convert(repository.save(customerEntity)));
    }

    public Optional<Customer> update(Customer entity) {
        CustomerEntity customerFound = repository.findOne(entity.getId());
        from(entity).merge(customerFound);
        repository.save(customerFound);

        return Optional.of(getConverter().convert(customerFound));
    }

    @Override
    public BaseRepositoryLegacy<CustomerEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<CustomerEntity, Customer> getConverter() {
        return converter;
    }
}
