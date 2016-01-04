package br.com.kproj.salesman.negotiation.view.helpers;


import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.negotiation.application.NegotiationApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BusinessProposalHelper {

    @Autowired
    private NegotiationApplication application;


    public List<BusinessProposal> getProposalsBy(Person client) {
        return application.findByClient(client);
    }
}
