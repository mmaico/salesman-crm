package br.com.kproj.salesman.administration.approval_negotiation.domain.model.approval;


public interface RequestApprovalEventHandler {

    void newRequestApproval(RequestApproval message);

    void evaluationWasCompleted(RequestApproval approval);
}
