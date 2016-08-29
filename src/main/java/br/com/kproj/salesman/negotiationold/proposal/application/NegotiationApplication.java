package br.com.kproj.salesman.negotiationold.proposal.application;

import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.enums.ProposalTemperature;
import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrder;
import br.com.kproj.salesman.infrastructure.service.ModelLegacyService;

import java.util.List;

public interface NegotiationApplication extends ModelLegacyService<BusinessProposalEntity> {

    BusinessProposalEntity register(BusinessProposalEntity businessProposalEntity);

    List<BusinessProposalEntity> findByClient(Person client);

    SalesOrder findSalesBy(BusinessProposalEntity businessProposalEntity);

    void changeTemperature(BusinessProposalEntity proposal, UserEntity changer, ProposalTemperature temperature);

}
