package br.com.kproj.salesman.delivery.tasks.infrastructure.persistence.springdata;

import br.com.kproj.salesman.infrastructure.entity.task.TaskEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface TaskRepositorySpringData extends BaseRepositoryLegacy<TaskEntity, Long> {

    @Query("SELECT t FROM TaskEntity AS t WHERE t.delivery = :deliveryId ORDER BY t.deadline ASC")
    Page<TaskEntity> findAllByDelivery(@Param("deliveryId") Long deliveryId, Pageable pageable);


}
