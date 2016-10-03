package br.com.kproj.salesman.incidents.infrastructure.persistence;


import br.com.kproj.salesman.incidents.domain.model.customer.Customer;
import br.com.kproj.salesman.incidents.domain.model.customer.CustomerRepository;
import br.com.kproj.salesman.incidents.infrastructure.persistence.springdata.AccountEntityRepositorySpringData;
import br.com.kproj.salesman.infrastructure.entity.accounts.AccountEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("customerRepositoryHibernate")
public class CustomerRepositoryHibernate extends BaseRespositoryImpl<Customer, AccountEntity> implements CustomerRepository {

    private AccountEntityRepositorySpringData repository;

    @Autowired
    public CustomerRepositoryHibernate(AccountEntityRepositorySpringData repository) {
        this.repository = repository;
    }

    @Override
    public BaseRepositoryLegacy<AccountEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<AccountEntity, Customer> getConverter() {
        return (accountEntity, args) -> new Customer(accountEntity.getId());
    }
}
