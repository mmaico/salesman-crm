package br.com.kproj.salesman.accounts.customers.domain.model.user;


import br.com.kproj.salesman.accounts.customers.domain.model.customer.Customer;
import br.com.kproj.salesman.accounts.customers.domain.model.customer.CustomerRepository;
import br.com.kproj.salesman.infrastructure.helpers.AutowireHelper;
import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;

import com.github.mmaico.shared.annotations.Model;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Model
public class User extends ModelIdentifiable {

    private Long id;

    @Autowired
    private CustomerRepository repository;

    public User() {
        AutowireHelper.autowire(this);
    }

    public Optional<Customer> save(Customer customer) {
        return repository.save(customer);
    }

    public Optional<Customer> update(Customer customer) {
        return repository.update(customer);
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static User user() {
        return new User();
    }
}
