package br.com.kproj.salesman.administration.approval_negotiation.domain.model.approver;


import br.com.kproj.salesman.infrastructure.repository.BaseRepository;

import java.util.Collection;

public interface ApproverRepository extends BaseRepository<Approver, Long> {


    Boolean hasApproversAvailable();

    Collection<Approver> getApproversAvailable();
}

