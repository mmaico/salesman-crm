package br.com.kproj.salesman.delivery.tasks.infrastructure.persistence.springdata;

import br.com.kproj.salesman.infrastructure.entity.task.ChecklistEntity;
import br.com.kproj.salesman.infrastructure.entity.task.TaskEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ChecklistRepositorySpringData extends BaseRepositoryLegacy<ChecklistEntity, Long> {

    @Query("SELECT c FROM ChecklistEntity AS c WHERE c.task = :task")
    Page<ChecklistEntity> findCheckListBy(@Param("task") TaskEntity taskEntity, Pageable pageable);

}
