package br.com.kproj.salesman.accounts.customers.domain.model.customer;


import br.com.kproj.salesman.infrastructure.repository.BaseRepository;

import java.util.Optional;

public interface CustomerRepository extends BaseRepository<Customer, Long> {


    Optional<Customer> update(Customer customer);
}

