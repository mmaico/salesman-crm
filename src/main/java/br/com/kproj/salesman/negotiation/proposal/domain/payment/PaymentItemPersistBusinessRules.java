package br.com.kproj.salesman.negotiation.proposal.domain.payment;


import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;

public interface PaymentItemPersistBusinessRules {

    public Boolean verifyRules(BusinessProposalEntity proposal);
}
