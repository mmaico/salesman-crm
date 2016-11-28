package br.com.kproj.salesman.negotiation.negotiation.infrastructure.persistence;

import br.com.kproj.salesman.infrastructure.entity.accounts.CustomerEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.negotiation.negotiation.domain.model.customer.Customer;
import br.com.kproj.salesman.negotiation.negotiation.domain.model.customer.CustomerRepository;
import br.com.kproj.salesman.negotiation.negotiation.infrastructure.persistence.springdata.CustomerEntityRepositorySpringData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("customerRepositoryHibernateNegotiationModule")
public class CustomerRepositoryHibernate extends BaseRespositoryImpl<Customer, CustomerEntity> implements CustomerRepository {


    @Autowired
    private CustomerEntityRepositorySpringData repository;


    @Override
    public BaseRepositoryLegacy<CustomerEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<CustomerEntity, Customer> getConverter() {
        return ((customerEntity, args) -> new Customer(customerEntity.getId()));
    }
}
