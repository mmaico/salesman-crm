package br.com.kproj.salesman.infrastructure.repository.task;


import br.com.kproj.salesman.infrastructure.entity.OperationRegionEntity;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.infrastructure.entity.task.TaskTemplateEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TaskTemplateRepository extends BaseRepositoryLegacy<TaskTemplateEntity, Long> {

    @Query("SELECT tt FROM TaskTemplateEntity AS tt WHERE tt.saleable = :saleable AND tt.parentId is null")
    List<TaskTemplateEntity> findTaskTemplateBy(@Param("saleable")SaleableUnitEntity saleable);

    @Query("SELECT tt FROM TaskTemplateEntity AS tt WHERE tt.saleable = :saleable AND tt.parentId is null AND tt.region = :region")
    List<TaskTemplateEntity> findTaskTemplateBy(@Param("saleable")SaleableUnitEntity saleable, @Param("region")OperationRegionEntity region);

    @Query("SELECT tt FROM TaskTemplateEntity AS tt WHERE tt.saleable = :saleable AND tt " +
            " NOT IN (SELECT child FROM TaskTemplateEntity AS tta JOIN tta.templatesChilds AS child " +
            "   WHERE tta.saleable = :saleable)")
    List<TaskTemplateEntity> findTaskTemplateRootBy(@Param("saleable")SaleableUnitEntity saleable);

    @Query("SELECT " +
            "   CASE WHEN count(*) > 0 " +
            "       THEN true " +
            "       ELSE false " +
            "   END  " +
            "FROM TaskTemplateEntity AS tt JOIN tt.templatesChilds AS taskChild " +
            "WHERE taskChild = :taskTemplateChild")
    Boolean isSomeonesSon(@Param("taskTemplateChild")TaskTemplateEntity taskTemplateEntityChild);

    @Query("SELECT tt FROM TaskTemplateEntity AS tt JOIN tt.templatesChilds AS taskChild " +
            "WHERE taskChild = :taskTemplateChild")
    Optional<TaskTemplateEntity> findParent(@Param("taskTemplateChild")TaskTemplateEntity taskTemplateEntityChild);

    @Query("SELECT tt FROM TaskTemplateEntity AS tt WHERE tt.id = :id")
    Optional<TaskTemplateEntity> getOne(@Param("id")Long id);
}
