package br.com.kproj.salesman.infrastructure.repository.task;


import br.com.kproj.salesman.infrastructure.entity.saleable.Product;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnit;
import br.com.kproj.salesman.infrastructure.entity.task.TaskTemplate;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TaskTemplateRepository extends BaseRepository<TaskTemplate, Long> {

    @Query("SELECT tt FROM TaskTemplate AS tt WHERE tt.saleable = :saleable")
    List<TaskTemplate> findTaskTemplateBy(@Param("saleable")SaleableUnit saleable);

    @Query("SELECT tt FROM TaskTemplate AS tt WHERE tt.saleable = :saleable AND tt " +
            " NOT IN (SELECT child FROM TaskTemplate AS tta JOIN tta.templatesChilds AS child " +
            "   WHERE tta.saleable = :saleable)")
    List<TaskTemplate> findTaskTemplateRootBy(@Param("saleable")SaleableUnit saleable);

    @Query("SELECT " +
            "   CASE WHEN count(*) > 0 " +
            "       THEN true " +
            "       ELSE false " +
            "   END  " +
            "FROM TaskTemplate AS tt JOIN tt.templatesChilds AS taskChild " +
            "WHERE taskChild = :taskTemplateChild")
    Boolean isSomeonesSon(@Param("taskTemplateChild")TaskTemplate taskTemplateChild);

    @Query("SELECT tt FROM TaskTemplate AS tt JOIN tt.templatesChilds AS taskChild " +
            "WHERE taskChild = :taskTemplateChild")
    Optional<TaskTemplate> findParent(@Param("taskTemplateChild")TaskTemplate taskTemplateChild);

    @Query("SELECT tt FROM TaskTemplate AS tt WHERE tt.id = :id")
    Optional<TaskTemplate> getOne(@Param("id")Long id);
}
