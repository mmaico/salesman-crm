package br.com.kproj.salesman.delivery.delivery.infrastructure.persistence.springdata;


import br.com.kproj.salesman.infrastructure.entity.delivery.WorkerEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface WorkerRepositorySpringdata extends BaseRepositoryLegacy<WorkerEntity, Long> {

    @Query("SELECT w FROM WorkerEntity AS w WHERE w.delivery.id = :id")
    Collection<WorkerEntity> findAll(@Param("id") Long deliveryId);


}
