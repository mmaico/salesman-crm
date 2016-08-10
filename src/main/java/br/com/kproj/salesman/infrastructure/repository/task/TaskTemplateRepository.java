package br.com.kproj.salesman.infrastructure.repository.task;


import br.com.kproj.salesman.infrastructure.entity.OperationRegion;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.infrastructure.entity.task.TaskTemplate;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TaskTemplateRepository extends BaseRepository<TaskTemplate, Long> {

    @Query("SELECT tt FROM TaskTemplate AS tt WHERE tt.saleable = :saleable AND tt.parentId is null")
    List<TaskTemplate> findTaskTemplateBy(@Param("saleable")SaleableUnitEntity saleable);

    @Query("SELECT tt FROM TaskTemplate AS tt WHERE tt.saleable = :saleable AND tt.parentId is null AND tt.region = :region")
    List<TaskTemplate> findTaskTemplateBy(@Param("saleable")SaleableUnitEntity saleable, @Param("region")OperationRegion region);

    @Query("SELECT tt FROM TaskTemplate AS tt WHERE tt.saleable = :saleable AND tt " +
            " NOT IN (SELECT child FROM TaskTemplate AS tta JOIN tta.templatesChilds AS child " +
            "   WHERE tta.saleable = :saleable)")
    List<TaskTemplate> findTaskTemplateRootBy(@Param("saleable")SaleableUnitEntity saleable);

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
