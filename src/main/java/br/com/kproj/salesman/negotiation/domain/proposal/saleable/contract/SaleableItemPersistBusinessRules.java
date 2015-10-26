package br.com.kproj.salesman.negotiation.domain.proposal.saleable.contract;


import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;

public interface SaleableItemPersistBusinessRules {

    public Boolean verifyRules(BusinessProposal proposal);
}
