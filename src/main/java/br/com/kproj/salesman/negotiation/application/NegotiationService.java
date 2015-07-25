package br.com.kproj.salesman.negotiation.application;

import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.service.ModelService;

public interface NegotiationService  extends ModelService<BusinessProposal> {

    public BusinessProposal register(BusinessProposal businessProposal);

}
