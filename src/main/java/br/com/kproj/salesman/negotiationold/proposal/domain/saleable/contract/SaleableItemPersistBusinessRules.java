package br.com.kproj.salesman.negotiationold.proposal.domain.saleable.contract;


import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;

public interface SaleableItemPersistBusinessRules {

    public Boolean verifyRules(BusinessProposalEntity proposal);
}
