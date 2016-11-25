package br.com.kproj.salesman.accounts.addresses.infrastructure.persistence.springdata;

import br.com.kproj.salesman.infrastructure.entity.accounts.AddressEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface AddressEntityRepositorySpringData extends BaseRepositoryLegacy<AddressEntity, Long> {

    @Query("SELECT a FROM AddressEntity AS a WHERE a.customer.id = :customerId")
    Page<AddressEntity> findAllBy(@Param("customerId") Long customerId, Pageable pageable);


}
