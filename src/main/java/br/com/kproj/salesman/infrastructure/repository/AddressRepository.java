package br.com.kproj.salesman.infrastructure.repository;


import br.com.kproj.salesman.infrastructure.entity.Address;
import br.com.kproj.salesman.infrastructure.repository.custom.PersonRepositoryCustom;

public interface AddressRepository extends BaseRepository<Address, Long>, PersonRepositoryCustom {


}
