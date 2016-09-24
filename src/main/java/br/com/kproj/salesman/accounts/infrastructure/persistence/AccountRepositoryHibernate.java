package br.com.kproj.salesman.accounts.infrastructure.persistence;

import br.com.kproj.salesman.accounts.domain.model.account.Account;
import br.com.kproj.salesman.accounts.domain.model.account.AccountRepository;
import br.com.kproj.salesman.accounts.infrastructure.persistence.springdata.AccountEntityRepositorySpringData;
import br.com.kproj.salesman.accounts.infrastructure.persistence.translate.AccountEntityToAccountConverter;
import br.com.kproj.salesman.infrastructure.entity.accounts.AccountEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepositoryHibernate extends BaseRespositoryImpl<Account, AccountEntity> implements AccountRepository {

    @Autowired
    private AccountEntityRepositorySpringData repository;

    @Autowired
    private AccountEntityToAccountConverter converter;



    @Override
    public BaseRepositoryLegacy<AccountEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<AccountEntity, Account> getConverter() {
        return converter;
    }
}
