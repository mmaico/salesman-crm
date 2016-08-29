package br.com.kproj.salesman.negotiation.view.helpers;


import br.com.kproj.salesman.infrastructure.entity.enums.ProposalTemperature;
import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrder;
import br.com.kproj.salesman.negotiationold.proposal.application.NegotiationApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class BusinessProposalHelper {

    private Map<ProposalTemperature, String> temperature = new HashMap<>();

    {
        temperature.put(ProposalTemperature.COLD, "/view-resource/assets/build/images/icons/icon_3.png" );
        temperature.put(ProposalTemperature.MOURN, "/view-resource/assets/build/images/icons/icon_2.png" );
        temperature.put(ProposalTemperature.HOT, "/view-resource/assets/build/images/icons/icon_1.png");
        temperature.put(ProposalTemperature.CLOSED_WON, "/view-resource/assets/build/images/icons/icon_4.png");
    }

    @Autowired
    private NegotiationApplication application;



    public List<BusinessProposalEntity> getProposalsBy(Person client) {
        return application.findByClient(client);
    }

    public String getTemperatureIcon(BusinessProposalEntity proposal) {
        if (proposal.getTemperature() == null) {
            return temperature.get(ProposalTemperature.COLD);
        }

        return temperature.get(proposal.getTemperature());
    }

    public Boolean isDone(Long  proposalId) {
        Optional<BusinessProposalEntity> result = application.getOne(proposalId);

        if (result.isPresent()) {
            return ProposalTemperature.CLOSED_WON.equals(result.get().getTemperature());
        }

        return Boolean.FALSE;
    }

    public BusinessProposalEntity load(BusinessProposalEntity proposal) {
        Optional<BusinessProposalEntity> result = application.getOne(proposal.getId());

        return result.isPresent() ? result.get() : null;
    }

    public Boolean isDone(BusinessProposalEntity proposal) {
        return isDone(proposal.getId());
    }

    public SalesOrder getByProposal(BusinessProposalEntity proposal) {
        return application.findSalesBy(proposal);
    }
}
