package br.com.kproj.salesman.infrastructure.repository.task;


import br.com.kproj.salesman.infrastructure.entity.task.ChecklistEntity;
import br.com.kproj.salesman.infrastructure.entity.task.TaskEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ChecklistEntityRepository extends BaseRepositoryLegacy<ChecklistEntity, Long> {

    @Query("SELECT c FROM Checklist AS c WHERE c.task = :task")
    List<ChecklistEntity> findCheckListBy(@Param("task") TaskEntity taskEntity);

    @Query("SELECT c FROM Checklist AS c WHERE c.id = :id")
    Optional<ChecklistEntity> getOne(@Param("id")Long id);

}
