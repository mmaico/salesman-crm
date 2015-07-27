package br.com.kproj.salesman.negotiation.domain.proposal.product;


import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;

public interface ProductItemPersistBusinessRules {

    public Boolean verifyRules(BusinessProposal proposal);
}
