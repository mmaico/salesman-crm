package br.com.kproj.salesman.accounts.addresses.infrastructure.persistence;


import br.com.kproj.salesman.accounts.addresses.domain.model.address.Address;
import br.com.kproj.salesman.accounts.addresses.domain.model.address.AddressRepository;
import br.com.kproj.salesman.accounts.addresses.domain.model.customer.Customer;
import br.com.kproj.salesman.accounts.addresses.infrastructure.persistence.springdata.AddressEntityRepositorySpringData;
import br.com.kproj.salesman.accounts.addresses.infrastructure.persistence.translate.AddressEntityToAddressConverter;
import br.com.kproj.salesman.infrastructure.entity.accounts.AddressEntity;
import br.com.kproj.salesman.infrastructure.entity.accounts.AddressEntity.Type;
import br.com.kproj.salesman.infrastructure.entity.accounts.CustomerEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import com.github.mmaico.clone.BusinessModelClone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.github.mmaico.dsl.ConditionalSet.set;

@Repository
public class AddressRepositoryHibernate extends BaseRespositoryImpl<Address, AddressEntity> implements AddressRepository {

    private AddressEntityRepositorySpringData repository;

    private AddressEntityToAddressConverter converter;

    @Autowired
    public AddressRepositoryHibernate(AddressEntityRepositorySpringData repository, AddressEntityToAddressConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public Page<Address> findAll(Customer customer, Pageable pageable) {
        Page<AddressEntity> result = repository.findAllBy(customer.getId(), pageable);
        List<Address> addresses = result.getContent().stream()
                .map(entity -> getConverter().convert(entity))
                .collect(Collectors.toList());

        return new PageImpl<>(addresses, pageable, result.getTotalElements());
    }

    @Override
    public Optional<Address> save(Address entity) {
        AddressEntity addressEntity = BusinessModelClone.from(entity).convertTo(AddressEntity.class);
        addressEntity.setType(Type.valueOf(entity.getType().name()));
        addressEntity.setCustomer(new CustomerEntity(entity.getCustomer().getId()));

        Address addressSaved = getConverter().convert(repository.save(addressEntity));
        return Optional.of(addressSaved);
    }

    @Override
    public Optional<Address> update(Address address) {
        AddressEntity entityLoaded = repository.findOne(address.getId());
        BusinessModelClone.from(address).merge(entityLoaded);

        Type type = Type.getByName(address.getType().name());

        set(entityLoaded)
                .when(address.hasField("type"))
                .setType(type == null ? entityLoaded.getType() : type);

        Address addressSaved = getConverter().convert(repository.save(entityLoaded));
        return Optional.of(addressSaved);
    }


    @Override
    public BaseRepositoryLegacy<AddressEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<AddressEntity, Address> getConverter() {
        return converter;
    }


}
