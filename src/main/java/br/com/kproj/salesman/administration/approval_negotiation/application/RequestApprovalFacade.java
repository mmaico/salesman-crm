package br.com.kproj.salesman.administration.approval_negotiation.application;

import br.com.kproj.salesman.administration.approval_negotiation.domain.model.approval.RequestApproval;
import br.com.kproj.salesman.administration.approval_negotiation.domain.model.approver.Approver;
import br.com.kproj.salesman.administration.approval_negotiation.domain.model.negotiation.Negotiation;
import br.com.kproj.salesman.infrastructure.entity.enums.ApproverStatus;
import br.com.kproj.salesman.infrastructure.service.ModelFacade;

import java.util.Optional;

public interface RequestApprovalFacade extends ModelFacade<RequestApproval> {


    Optional<RequestApproval> register(RequestApproval requestApproval);

    void evaluationApprover(Negotiation negotiation, Approver approver, ApproverStatus status);

}
