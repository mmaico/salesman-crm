package br.com.kproj.salesman.delivery.tasks.infrastructure.persistence.springdata;

import br.com.kproj.salesman.infrastructure.entity.task.TaskResponsibleEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("taskResponsibleRepositorySpringDataDeliveryModule")
public interface TaskResponsibleRepositorySpringData extends BaseRepositoryLegacy<TaskResponsibleEntity, Long> {

    @Query("SELECT t FROM TaskResponsibleEntity AS t WHERE t.task.id = :taskId")
    Page<TaskResponsibleEntity> findAllByTask(@Param("taskId") Long taskId, Pageable pageable);

}
