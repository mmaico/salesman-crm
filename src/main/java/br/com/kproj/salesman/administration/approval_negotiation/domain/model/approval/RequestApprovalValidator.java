package br.com.kproj.salesman.administration.approval_negotiation.domain.model.approval;


public interface RequestApprovalValidator {

    void isValidToStartRequestApproval(RequestApproval request);
}
