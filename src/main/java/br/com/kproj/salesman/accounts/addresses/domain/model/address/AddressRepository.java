package br.com.kproj.salesman.accounts.addresses.domain.model.address;


import br.com.kproj.salesman.accounts.addresses.domain.model.customer.Customer;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AddressRepository extends BaseRepository<Address, Long> {

    Page<Address> findAll(Customer customer, Pageable pageable);

    Optional<Address> update(Address entity);

}

