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
    public BaseRepositoryLegacy<CustomerEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<CustomerEntity, Customer> getConverter() {
        return converter;
    }
}
