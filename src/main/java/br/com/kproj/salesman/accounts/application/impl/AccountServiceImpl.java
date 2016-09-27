package br.com.kproj.salesman.accounts.application.impl;

import br.com.kproj.salesman.accounts.application.AccountFacade;
import br.com.kproj.salesman.accounts.domain.model.account.Account;
import br.com.kproj.salesman.accounts.domain.model.account.AccountRepository;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl extends BaseModelServiceImpl<Account> implements AccountFacade {

    private AccountRepository repository;

    @Autowired
    public AccountServiceImpl(AccountRepository repository) {
        this.repository = repository;
    }


    @Override
    public BaseRepository<Account, Long> getRepository() {
        return repository;
    }
}
