package br.com.kproj.salesman.infrastructure.repository;


import br.com.kproj.salesman.infrastructure.entity.person.client.Client;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SalesOrderRepository extends BaseRepositoryLegacy<SalesOrder, Long> {

    @Query("SELECT so FROM SalesOrder AS so WHERE so.client = :client")
    List<SalesOrder> getOrdersByClient(@Param("client")Client client);

    Optional<SalesOrder> findByProposal(@Param("domain")BusinessProposal proposal);

    @Query("SELECT so FROM SalesOrder AS so ORDER BY so.creationDate DESC")
    List<SalesOrder> findAllOrdered();
}
