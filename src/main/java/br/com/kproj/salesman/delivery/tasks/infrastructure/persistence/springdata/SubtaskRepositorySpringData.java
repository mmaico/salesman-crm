package br.com.kproj.salesman.delivery.tasks.infrastructure.persistence.springdata;

import br.com.kproj.salesman.infrastructure.entity.task.SubtaskEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface SubtaskRepositorySpringData extends BaseRepositoryLegacy<SubtaskEntity, Long> {


    @Query("SELECT s FROM SubtaskEntity AS s WHERE s.parent.id = :rootTaskId")
    Page<SubtaskEntity> findAllByRoot(@Param("rootTaskId") Long rootTaskId, Pageable pageable);

}
