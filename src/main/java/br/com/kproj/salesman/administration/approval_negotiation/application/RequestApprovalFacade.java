package br.com.kproj.salesman.administration.approval_negotiation.application;

import br.com.kproj.salesman.administration.approval_negotiation.domain.model.approval.EvaluationRequest;
import br.com.kproj.salesman.administration.approval_negotiation.domain.model.approval.RequestApproval;
import br.com.kproj.salesman.infrastructure.service.ModelFacade;

import java.util.Optional;

public interface RequestApprovalFacade extends ModelFacade<RequestApproval> {


    Optional<RequestApproval> register(RequestApproval requestApproval);

    void makeEvaluation(EvaluationRequest request);

}
