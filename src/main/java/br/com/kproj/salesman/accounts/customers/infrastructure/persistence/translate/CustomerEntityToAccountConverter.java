package br.com.kproj.salesman.accounts.customers.infrastructure.persistence.translate;

import br.com.kproj.salesman.accounts.customers.domain.model.customer.Customer;
import br.com.kproj.salesman.accounts.customers.domain.model.customer.CustomerBuilder;
import br.com.kproj.salesman.infrastructure.entity.accounts.CustomerEntity;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.stereotype.Component;


@Component
public class CustomerEntityToAccountConverter implements Converter<CustomerEntity, Customer> {

    @Override
    public Customer convert(CustomerEntity customerEntity, Object... args) {
        if (customerEntity == null) return null;

        Customer account = CustomerBuilder.createAccount(customerEntity.getId())
                .withName(customerEntity.getName())
                .withDescription(customerEntity.getDescription())
                .withSite(customerEntity.getSite()).build();

        return account;
    }
}
