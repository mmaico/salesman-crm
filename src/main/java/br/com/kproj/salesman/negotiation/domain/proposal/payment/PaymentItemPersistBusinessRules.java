package br.com.kproj.salesman.negotiation.domain.proposal.payment;


import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;

public interface PaymentItemPersistBusinessRules {

    public Boolean verifyRules(BusinessProposal proposal);
}
