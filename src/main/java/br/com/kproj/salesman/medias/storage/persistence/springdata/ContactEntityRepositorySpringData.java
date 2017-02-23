package br.com.kproj.salesman.medias.storage.persistence.springdata;

import br.com.kproj.salesman.infrastructure.entity.ContactEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ContactEntityRepositorySpringData extends BaseRepositoryLegacy<ContactEntity, Long> {


    @Query("SELECT c FROM ContactEntity AS c WHERE c.customer.id = :customerId")
    Page<ContactEntity> findAll(@Param("customerId") Long customerId, Pageable pageable);

}
