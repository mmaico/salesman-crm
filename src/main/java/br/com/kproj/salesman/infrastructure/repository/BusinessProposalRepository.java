package br.com.kproj.salesman.infrastructure.repository;


import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrderEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BusinessProposalRepository extends BaseRepositoryLegacy<BusinessProposalEntity, Long> {


    //List<BusinessProposalEntity> findByClient(@Param("client") Person client);

    @Query("SELECT so FROM SalesOrderEntity AS so WHERE so.proposal = :proposal")
    SalesOrderEntity findByProposal(@Param("proposal") BusinessProposalEntity proposal);
}
