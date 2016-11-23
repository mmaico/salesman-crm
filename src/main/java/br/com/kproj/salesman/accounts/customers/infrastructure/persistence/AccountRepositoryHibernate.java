package br.com.kproj.salesman.accounts.customers.infrastructure.persistence;

import br.com.kproj.salesman.accounts.customers.domain.model.customer.Customer;
import br.com.kproj.salesman.accounts.customers.domain.model.customer.CustomerRepository;
import br.com.kproj.salesman.accounts.customers.infrastructure.persistence.springdata.AccountEntityRepositorySpringData;
import br.com.kproj.salesman.accounts.customers.infrastructure.persistence.translate.AccountEntityToAccountConverter;
import br.com.kproj.salesman.infrastructure.entity.accounts.AccountEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepositoryHibernate extends BaseRespositoryImpl<Customer, AccountEntity> implements CustomerRepository {

    private AccountEntityRepositorySpringData repository;

    private AccountEntityToAccountConverter converter;

    @Autowired
    public AccountRepositoryHibernate(AccountEntityRepositorySpringData repository, AccountEntityToAccountConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }


    @Override
    public BaseRepositoryLegacy<AccountEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<AccountEntity, Customer> getConverter() {
        return converter;
    }
}
