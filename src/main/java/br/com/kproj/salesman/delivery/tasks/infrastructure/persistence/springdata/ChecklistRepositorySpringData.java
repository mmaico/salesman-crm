package br.com.kproj.salesman.delivery.tasks.infrastructure.persistence.springdata;

import br.com.kproj.salesman.infrastructure.entity.task.ChecklistEntity;
import br.com.kproj.salesman.infrastructure.entity.task.TaskEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ChecklistRepositorySpringData extends BaseRepositoryLegacy<ChecklistEntity, Long> {

    @Query("SELECT c FROM ChecklistEntity AS c WHERE c.task = :task")
    List<ChecklistEntity> findCheckListBy(@Param("task") TaskEntity taskEntity);

}
