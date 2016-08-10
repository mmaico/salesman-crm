package br.com.kproj.salesman.infrastructure.repository.task;


import br.com.kproj.salesman.infrastructure.entity.task.ChecklistTemplate;
import br.com.kproj.salesman.infrastructure.entity.task.TaskTemplate;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChecklistTemplateRepository extends BaseRepository<ChecklistTemplate, Long> {

    @Query("SELECT ct FROM ChecklistTemplate AS ct WHERE ct.taskTemplate = :taskTemplate")
    List<ChecklistTemplate> findCheckListBy(@Param("taskTemplate") TaskTemplate taskTemplate);

}
