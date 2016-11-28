package br.com.kproj.salesman.negotiation.negotiation.domain.model.approval;


import br.com.kproj.salesman.negotiation.negotiation.domain.model.negotiation.Negotiation;

public interface ApprovalProcessRepository {

    Boolean isInApprovalProcess(Negotiation negotiation);

}
