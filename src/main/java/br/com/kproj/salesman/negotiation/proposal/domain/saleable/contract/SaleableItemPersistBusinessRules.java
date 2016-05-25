package br.com.kproj.salesman.negotiation.proposal.domain.saleable.contract;


import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;

public interface SaleableItemPersistBusinessRules {

    public Boolean verifyRules(BusinessProposal proposal);
}
