package br.com.kproj.salesman.accounts.addresses.infrastructure.persistence;


import br.com.kproj.salesman.accounts.addresses.domain.model.customer.Customer;
import br.com.kproj.salesman.accounts.addresses.domain.model.customer.CustomerRepository;
import br.com.kproj.salesman.accounts.addresses.infrastructure.persistence.springdata.CustomerEntityRepositorySpringData;
import br.com.kproj.salesman.infrastructure.entity.accounts.CustomerEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("customerRepositoryHibernateAddressModule")
public class CustomerRepositoryHibernate extends BaseRespositoryImpl<Customer, CustomerEntity> implements CustomerRepository {

    private CustomerEntityRepositorySpringData repository;



    @Autowired
    public CustomerRepositoryHibernate(CustomerEntityRepositorySpringData repository) {
        this.repository = repository;
    }

    @Override
    public BaseRepositoryLegacy<CustomerEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<CustomerEntity, Customer> getConverter() {
        return (customerEntity, args) -> new Customer(customerEntity.getId());
    }


}
