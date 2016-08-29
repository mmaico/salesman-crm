package br.com.kproj.salesman.negotiation2.domain.model.approval;


import br.com.kproj.salesman.negotiation2.domain.model.negotiation.Negotiation;

public interface ApprovalProcessRepository {

    Boolean isInApprovalProcess(Negotiation negotiation);

}
