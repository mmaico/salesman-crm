package br.com.kproj.salesman.delivery.workspaces.infrastructure.persistence.springdata;


import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.WorkspaceUnit;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrderEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface WorkspaceUnitRepositorySpringdata extends BaseRepositoryLegacy<WorkspaceUnit, Long> {


    @Query("SELECT wu FROM WorkspaceUnit AS wu WHERE wu.salesOrder = :salesOrder AND wu.user = :user ")
    Optional<WorkspaceUnit> findBySalesOrderAndUser(@Param("salesOrder") SalesOrderEntity salesOrderEntity,
                                                    @Param("user") UserEntity user);

//    @Query("SELECT distinct wu.user FROM WorkspaceUnit AS wu")
//    List<UserEntity> findUsersWithSignedDelivery();
//
//    @Query("SELECT DISTINCT wu.user FROM WorkspaceUnit AS wu WHERE wu.salesOrder =:salesOrder")
//    List<UserEntity> findUserWithItemsInWorkspace(@Param("salesOrder") SalesOrderEntity salesOrderEntity);
}
