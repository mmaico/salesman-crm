package br.com.kproj.salesman.infrastructure.repository;


import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BusinessProposalRepository extends BaseRepositoryLegacy<BusinessProposalEntity, Long> {


    //List<BusinessProposalEntity> findByClient(@Param("client") Person client);

    @Query("SELECT so FROM SalesOrder AS so WHERE so.proposal = :proposal")
    SalesOrder findByProposal(@Param("proposal") BusinessProposalEntity proposal);
}
