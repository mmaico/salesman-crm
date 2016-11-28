package br.com.kproj.salesman.negotiation.negotiation.infrastructure.persistence.translate;

import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.negotiation.negotiation.domain.model.negotiation.Negotiation;
import br.com.kproj.salesman.negotiation.negotiation.domain.model.negotiation.NegotiationBuilder;
import br.com.kproj.salesman.negotiation.negotiation.domain.model.negotiation.Temperature;
import org.springframework.stereotype.Component;


@Component
public class BusinessProposalEntityToNegotiationConverter implements Converter<BusinessProposalEntity, Negotiation> {




    @Override
    public Negotiation convert(BusinessProposalEntity proposalEntity, Object... args) {

        NegotiationBuilder negotiationBuilder = NegotiationBuilder.createNegotiation(proposalEntity.getId())
                .withCareOf(proposalEntity.getCareOf())
                .withDeliveryForeCast(proposalEntity.getDeliveryForeCast())
                .withIntroduction(proposalEntity.getIntroduction())
                .withCustomer(proposalEntity.getCustomerId())
                .withRegion(proposalEntity.getRegionId())
                .withSeller(proposalEntity.getSellerId())
                .withDiscount(proposalEntity.getDiscount())
                .withAmmountPayable(proposalEntity.getAmmountPayable());

        negotiationBuilder.withTemperature(Temperature.valueOf(proposalEntity.getTemperature().name()));

        return negotiationBuilder.build();
    }
}
