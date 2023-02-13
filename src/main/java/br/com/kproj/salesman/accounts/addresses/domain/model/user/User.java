package br.com.kproj.salesman.accounts.addresses.domain.model.user;


import br.com.kproj.salesman.accounts.addresses.domain.model.address.Address;
import br.com.kproj.salesman.accounts.addresses.domain.model.address.AddressRepository;
import br.com.kproj.salesman.accounts.customers.domain.services.AddressToCustomerService;
import br.com.kproj.salesman.infrastructure.helpers.AutowireHelper;
import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;

import com.github.mmaico.shared.annotations.Model;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Model
public class User extends ModelIdentifiable {

    private Long id;

    @Autowired
    private AddressRepository repository;

    public User() {
        AutowireHelper.autowire(this);
    }

    public AddressToCustomerService save(Address address) {
        return customer -> {
            address.setCustomer(customer);
            return repository.save(address);
        };
    }

    public Optional<Address> update(Address address) {
        return repository.update(address);
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
