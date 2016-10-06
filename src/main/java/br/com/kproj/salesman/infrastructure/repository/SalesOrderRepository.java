package br.com.kproj.salesman.infrastructure.repository;


import br.com.kproj.salesman.infrastructure.entity.person.client.Client;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrderEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SalesOrderRepository extends BaseRepositoryLegacy<SalesOrderEntity, Long> {

    @Query("SELECT so FROM SalesOrderEntity AS so WHERE so.client = :client")
    List<SalesOrderEntity> getOrdersByClient(@Param("client")Client client);

    Optional<SalesOrderEntity> findByProposal(@Param("domain")BusinessProposalEntity proposal);

    @Query("SELECT so FROM SalesOrderEntity AS so ORDER BY so.creationDate DESC")
    List<SalesOrderEntity> findAllOrdered();
}
