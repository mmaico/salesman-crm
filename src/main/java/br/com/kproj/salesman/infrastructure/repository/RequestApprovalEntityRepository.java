package br.com.kproj.salesman.infrastructure.repository;


import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;
import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.RequestApprovalEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RequestApprovalEntityRepository extends BaseRepositoryLegacy<RequestApprovalEntity, Long> {


    //query original SELECT ra FROM RequestApprovalEntity AS ra WHERE ra.proposal = :proposal AND ra.status = 'WAITING'
    @Query("SELECT ra FROM RequestApprovalEntity AS ra WHERE ra.proposal = :proposal")
    Optional<RequestApprovalEntity> findByProposal(@Param("proposal")BusinessProposalEntity proposal);

    @Query("SELECT ra FROM RequestApprovalEntity AS ra WHERE ra.proposal = :proposal ORDER BY ra.id DESC")
    Page<RequestApprovalEntity> findLastRequestApprovals(@Param("proposal")BusinessProposalEntity proposal, Pageable pageable);

    //AND ra.status = 'APPROVED'
    @Query("SELECT " +
            " CASE WHEN count(*) > 0 " +
            "      THEN true " +
            "      ELSE false " +
            " END " +
            "FROM RequestApprovalEntity AS ra WHERE ra.proposal = :proposal")
    Boolean hasRequestApprovalApproved(@Param("proposal")BusinessProposalEntity proposal);

}
