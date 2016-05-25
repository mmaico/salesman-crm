package br.com.kproj.salesman.negotiation.proposal.domain.payment;


import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;

public interface PaymentItemPersistBusinessRules {

    public Boolean verifyRules(BusinessProposal proposal);
}
