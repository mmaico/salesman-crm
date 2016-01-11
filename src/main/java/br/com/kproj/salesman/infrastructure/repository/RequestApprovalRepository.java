package br.com.kproj.salesman.infrastructure.repository;


import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.RequestApproval;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RequestApprovalRepository extends BaseRepository<RequestApproval, Long> {


    @Query("SELECT ra FROM RequestApproval AS ra WHERE ra.proposal = :proposal AND ra.status = 'WAITING'")
    Optional<RequestApproval> findByProposal(@Param("proposal")BusinessProposal proposal);

    @Query("SELECT ra FROM RequestApproval AS ra WHERE ra.proposal = :proposal ORDER BY ra.id DESC")
    Page<RequestApproval> findLastRequestApprovals(@Param("proposal")BusinessProposal proposal, Pageable pageable);

    @Query("SELECT " +
            " CASE WHEN count(*) > 0 " +
            "      THEN true " +
            "      ELSE false " +
            " END " +
            "FROM RequestApproval AS ra WHERE ra.proposal = :proposal AND ra.status = 'APPROVED'")
    Boolean hasRequestApprovalApproved(@Param("proposal")BusinessProposal proposal);

}
