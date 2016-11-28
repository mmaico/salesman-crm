package br.com.kproj.salesman.negotiation.negotiation.infrastructure.persistence.springdata;

import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;
import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.RequestApprovalEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("approvalProcessRepositorySpringDataNegotiationModule")
public interface ApprovalProcessRepositorySpringData extends BaseRepositoryLegacy<RequestApprovalEntity, Long> {

    //AND ra.status = 'WAITING'
    @Query("SELECT ra FROM RequestApprovalEntity AS ra WHERE ra.proposal = :proposal")
    Optional<RequestApprovalEntity> findOne(@Param("proposal") BusinessProposalEntity proposal);


}
