package br.com.kproj.salesman.infrastructure.repository.task;


import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnit;
import br.com.kproj.salesman.infrastructure.entity.task.TaskTemplate;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskTemplateRepository extends BaseRepository<TaskTemplate, Long> {

    @Query("SELECT tt FROM TaskTemplate AS tt WHERE tt.saleable = :saleable")
    List<TaskTemplate> findTaskTemplateBy(@Param("saleable")SaleableUnit saleable);
}
