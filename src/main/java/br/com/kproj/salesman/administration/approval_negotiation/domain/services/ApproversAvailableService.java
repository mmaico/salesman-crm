package br.com.kproj.salesman.administration.approval_negotiation.domain.services;

import br.com.kproj.salesman.administration.approval_negotiation.domain.model.approval.RequestApproval;
import br.com.kproj.salesman.administration.approval_negotiation.domain.model.approver.Approver;

import java.util.Collection;
import java.util.Optional;

@FunctionalInterface
public interface ApproversAvailableService {

    Optional<RequestApproval> withAvailable(Collection<Approver> approvers);
}
