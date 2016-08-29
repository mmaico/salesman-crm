package br.com.kproj.salesman.accounts.infrastructure.persistence.translate;

import br.com.kproj.salesman.accounts.domain.model.account.Account;
import br.com.kproj.salesman.accounts.domain.model.account.AccountBuilder;
import br.com.kproj.salesman.infrastructure.entity.accounts.AccountEntity;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.stereotype.Component;


@Component
public class AccountEntityToAccountConverter implements Converter<AccountEntity, Account> {

    @Override
    public Account convert(AccountEntity accountEntity, Object... args) {
        if (accountEntity == null) return null;

        Account account = AccountBuilder.createAccount(accountEntity.getId())
                .withName(accountEntity.getName())
                .withDescription(accountEntity.getDescription())
                .withEmployers(accountEntity.getEmployers())
                .withPhone(accountEntity.getPhone())
                .withSite(accountEntity.getSite()).build();

        return account;
    }
}
