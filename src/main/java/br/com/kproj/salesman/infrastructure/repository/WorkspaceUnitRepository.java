package br.com.kproj.salesman.infrastructure.repository;


import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.WorkspaceUnit;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrderEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface WorkspaceUnitRepository extends BaseRepositoryLegacy<WorkspaceUnit, Long> {


    @Query("SELECT wu FROM WorkspaceUnit AS wu WHERE wu.salesOrder = :salesOrder AND wu.user = :user ")
    Optional<WorkspaceUnit> findBySalesOrderAndUser(@Param("salesOrder")SalesOrderEntity salesOrderEntity,
                                                    @Param("user") UserEntity user);

    @Query("SELECT so FROM SalesOrder AS so WHERE so NOT IN " +
            " (SELECT wu.salesOrder FROM WorkspaceUnit AS wu ) ")
    List<SalesOrderEntity> findSalesOrderOutActDelivery();

    @Query("SELECT DISTINCT wu.salesOrder FROM WorkspaceUnit AS wu ORDER BY wu.salesOrder.deliveryForecast")
    List<SalesOrderEntity> findSalesOrderNotInWorkspace();

    @Query("SELECT wu.salesOrder FROM WorkspaceUnit AS wu WHERE wu.user =:user")
    List<SalesOrderEntity> findByUser(@Param("user") UserEntity user);


    @Query("SELECT distinct wu.user FROM WorkspaceUnit AS wu")
    List<UserEntity> findUsersWithSignedDelivery();

    @Query("SELECT DISTINCT wu.user FROM WorkspaceUnit AS wu WHERE wu.salesOrder =:salesOrder")
    List<UserEntity> findUserWithItemsInWorkspace(@Param("salesOrder") SalesOrderEntity salesOrderEntity);
}
