package br.com.kproj.salesman.negotiation.approval.application;

import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.enums.ApproverStatus;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.RequestApproval;
import br.com.kproj.salesman.infrastructure.service.ModelService;

import java.util.Optional;

public interface RequestApprovalApplication extends ModelService<RequestApproval> {


    Optional<RequestApproval> register(RequestApproval requestApproval);

    void evaluationApprover(BusinessProposal proposal, User user, ApproverStatus status);

    Optional<RequestApproval> hasRequestApprovalWaitingfor(BusinessProposal proposal);

    Optional<RequestApproval> findLastRequestApproval(BusinessProposal proposal);

}
