package br.com.kproj.salesman.infrastructure.repository;


import br.com.kproj.salesman.infrastructure.entity.ActDeliverySales;
import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ActDeliverySalesRepository extends BaseRepository<ActDeliverySales, Long> {


    @Query("SELECT ads FROM ActDeliverySales AS ads WHERE ads.salesOrder = :salesOrder AND ads.user = :user ")
    Optional<ActDeliverySales> findBySalesOrderAndUser(@Param("salesOrder")SalesOrder salesOrder,
                                                       @Param("user") User user);

    @Query("SELECT so FROM SalesOrder AS so WHERE so NOT IN " +
            " (SELECT ads.salesOrder FROM ActDeliverySales AS ads ) ")
    List<SalesOrder> findSalesOrderOutActDelivery();

    @Query("SELECT DISTINCT so.salesOrder FROM ActDeliverySales AS so ORDER BY so.salesOrder.deliveryForecast")
    List<SalesOrder> findSalesOrderInActDelivery();

    @Query("SELECT ads.salesOrder FROM ActDeliverySales AS ads WHERE ads.user =:user")
    List<SalesOrder> findByUser(@Param("user") User user);

    @Query("SELECT distinct ads.user FROM ActDeliverySales AS ads")
    List<User> findUsersWithSignedDelivery();

}
