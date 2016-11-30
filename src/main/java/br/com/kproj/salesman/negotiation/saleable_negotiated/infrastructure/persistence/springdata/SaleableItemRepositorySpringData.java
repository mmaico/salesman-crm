package br.com.kproj.salesman.negotiation.saleable_negotiated.infrastructure.persistence.springdata;

import br.com.kproj.salesman.infrastructure.entity.proposal.ProposalSaleableItemEntity;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.stereotype.Repository;

@Repository("saleableItemRepositoryNegotiatedSaleableModule")
public interface SaleableItemRepositorySpringData extends BaseRepositoryLegacy<ProposalSaleableItemEntity, Long> {


}
