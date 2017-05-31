package br.com.kproj.salesman.administration.approval_negotiation.domain.model.approver;


public interface ApproverValidator {

    void exists(Approver approver);


    void isValid(Approver approver);


}
