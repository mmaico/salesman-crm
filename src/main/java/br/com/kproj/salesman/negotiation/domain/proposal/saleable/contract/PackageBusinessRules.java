package br.com.kproj.salesman.negotiation.domain.proposal.saleable.contract;


import br.com.kproj.salesman.infrastructure.entity.proposal.ProposalSaleableItem;

import java.util.List;

public interface PackageBusinessRules {

    public Boolean verifyRules(List<ProposalSaleableItem> saleableItems);
}
