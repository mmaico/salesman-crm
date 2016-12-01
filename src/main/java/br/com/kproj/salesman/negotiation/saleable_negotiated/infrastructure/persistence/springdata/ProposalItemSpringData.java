package br.com.kproj.salesman.negotiation.saleable_negotiated.infrastructure.persistence.springdata;

import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalItemEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository("proposalItemSpringDataNegotiatedSaleableModule")
public interface ProposalItemSpringData extends BaseRepositoryLegacy<BusinessProposalItemEntity, Long> {

    @Query("SELECT item FROM BusinessProposalItemEntity AS item WHERE item.businessProposal.id = :businessProposalId")
    Collection<BusinessProposalItemEntity> findAll(@Param("businessProposalId") Long businessProposalId);

}
