package br.com.kproj.salesman.administration.approval_negotiation.infrastructure.persistence.springdata;


import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;
import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.RequestApprovalEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RequestApprovalEntityRepository extends BaseRepositoryLegacy<RequestApprovalEntity, Long> {

    @Query("SELECT request FROM RequestApprovalEntity AS request WHERE request.proposal = :proposal")
    List<RequestApprovalEntity> findOne(@Param("proposal")BusinessProposalEntity proposal);


}
