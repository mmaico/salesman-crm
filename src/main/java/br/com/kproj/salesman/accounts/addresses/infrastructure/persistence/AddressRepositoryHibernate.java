package br.com.kproj.salesman.accounts.addresses.infrastructure.persistence;


import br.com.kproj.salesman.accounts.addresses.domain.model.address.Address;
import br.com.kproj.salesman.accounts.addresses.domain.model.address.AddressRepository;
import br.com.kproj.salesman.accounts.addresses.infrastructure.persistence.springdata.AddressEntityRepositorySpringData;
import br.com.kproj.salesman.accounts.addresses.infrastructure.persistence.translate.AddressEntityToAddressConverter;
import br.com.kproj.salesman.infrastructure.entity.accounts.AddressEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
    public BaseRepositoryLegacy<AddressEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<AddressEntity, Address> getConverter() {
        return converter;
    }
}
