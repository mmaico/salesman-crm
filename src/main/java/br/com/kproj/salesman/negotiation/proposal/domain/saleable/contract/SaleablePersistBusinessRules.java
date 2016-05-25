package br.com.kproj.salesman.negotiation.proposal.domain.saleable.contract;


import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnit;

public interface SaleablePersistBusinessRules {

    public Boolean verifyRules(SaleableUnit saleableUnit);
}
