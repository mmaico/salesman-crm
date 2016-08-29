package br.com.kproj.salesman.negotiationold.proposal.domain.saleable.contract;


import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;

public interface SaleablePersistBusinessRules {

    public Boolean verifyRules(SaleableUnitEntity saleableUnit);
}
