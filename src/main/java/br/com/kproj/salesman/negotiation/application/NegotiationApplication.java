package br.com.kproj.salesman.negotiation.application;

import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.enums.ProposalTemperature;
import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrder;
import br.com.kproj.salesman.infrastructure.service.ModelService;

import java.util.List;

public interface NegotiationApplication extends ModelService<BusinessProposal> {

    BusinessProposal register(BusinessProposal businessProposal);

    List<BusinessProposal> findByClient(Person client);

    SalesOrder findSalesBy(BusinessProposal businessProposal);

    void changeTemperature(BusinessProposal proposal, User changer, ProposalTemperature temperature);

}
