package br.com.kproj.salesman.infrastructure.repository;


import br.com.kproj.salesman.infrastructure.entity.WorkspaceUnit;
import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface WorkspaceUnitRepository extends BaseRepository<WorkspaceUnit, Long> {


    @Query("SELECT wu FROM WorkspaceUnit AS wu WHERE wu.salesOrder = :salesOrder AND wu.user = :user ")
    Optional<WorkspaceUnit> findBySalesOrderAndUser(@Param("salesOrder")SalesOrder salesOrder,
                                                    @Param("user") User user);

    @Query("SELECT so FROM SalesOrder AS so WHERE so NOT IN " +
            " (SELECT wu.salesOrder FROM WorkspaceUnit AS wu ) ")
    List<SalesOrder> findSalesOrderOutActDelivery();

    @Query("SELECT DISTINCT wu.salesOrder FROM WorkspaceUnit AS wu ORDER BY wu.salesOrder.deliveryForecast")
    List<SalesOrder> findSalesOrderNotInWorkspace();

    @Query("SELECT wu.salesOrder FROM WorkspaceUnit AS wu WHERE wu.user =:user")
    List<SalesOrder> findByUser(@Param("user") User user);


    @Query("SELECT distinct wu.user FROM WorkspaceUnit AS wu")
    List<User> findUsersWithSignedDelivery();

    @Query("SELECT DISTINCT wu.user FROM WorkspaceUnit AS wu WHERE wu.salesOrder =:salesOrder")
    List<User> findUserWithItemsInWorkspace(@Param("salesOrder") SalesOrder salesOrder);
}
