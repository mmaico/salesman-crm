package br.com.kproj.salesman.infrastructure.repository.task;


import br.com.kproj.salesman.infrastructure.entity.task.ChecklistTemplateEntity;
import br.com.kproj.salesman.infrastructure.entity.task.TaskTemplateEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChecklistTemplateRepository extends BaseRepositoryLegacy<ChecklistTemplateEntity, Long> {

    @Query("SELECT ct FROM ChecklistTemplate AS ct WHERE ct.taskTemplate = :taskTemplate")
    List<ChecklistTemplateEntity> findCheckListBy(@Param("taskTemplate") TaskTemplateEntity taskTemplateEntity);

}
