package br.com.kproj.salesman.administration.approval_negotiation.domain.model.approval;


import br.com.kproj.salesman.administration.approval_negotiation.domain.model.negotiation.Negotiation;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;

import java.util.Optional;

public interface RequestApprovalRepository extends BaseRepository<RequestApproval, Long> {


    Optional<RequestApproval> findOne(Negotiation negotiation);
}

