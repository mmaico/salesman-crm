package br.com.kproj.salesman.accounts.customers.infrastructure.persistence.translate;

import br.com.kproj.salesman.accounts.customers.domain.model.customer.Customer;
import br.com.kproj.salesman.accounts.customers.domain.model.customer.CustomerBuilder;
import br.com.kproj.salesman.infrastructure.entity.accounts.AccountEntity;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.stereotype.Component;


@Component
public class AccountEntityToAccountConverter implements Converter<AccountEntity, Customer> {

    @Override
    public Customer convert(AccountEntity accountEntity, Object... args) {
        if (accountEntity == null) return null;

        Customer account = CustomerBuilder.createAccount(accountEntity.getId())
                .withName(accountEntity.getName())
                .withDescription(accountEntity.getDescription())
                .withEmployers(accountEntity.getEmployers())
                .withPhone(accountEntity.getPhone())
                .withSite(accountEntity.getSite()).build();

        return account;
    }
}
