package br.com.kproj.salesman.negotiationold.proposal.domain.payment;


import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;

public interface PaymentItemPersistBusinessRules {

    public Boolean verifyRules(BusinessProposalEntity proposal);
}
