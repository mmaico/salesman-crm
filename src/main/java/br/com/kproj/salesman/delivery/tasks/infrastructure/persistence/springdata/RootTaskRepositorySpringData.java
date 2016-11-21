package br.com.kproj.salesman.delivery.tasks.infrastructure.persistence.springdata;

import br.com.kproj.salesman.infrastructure.entity.task.RootTaskEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface RootTaskRepositorySpringData extends BaseRepositoryLegacy<RootTaskEntity, Long> {

    @Query("SELECT r FROM RootTaskEntity AS r JOIN r.task AS t WHERE t.delivery.id = :deliveryId")
    Page<RootTaskEntity> findAllByDelivery(@Param("deliveryId") Long id, Pageable pageable);

}
