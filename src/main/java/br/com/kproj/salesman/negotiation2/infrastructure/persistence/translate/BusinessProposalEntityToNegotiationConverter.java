package br.com.kproj.salesman.negotiation2.infrastructure.persistence.translate;

import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.negotiation2.domain.model.negotiation.Negotiation;
import br.com.kproj.salesman.negotiation2.domain.model.negotiation.NegotiationBuilder;
import br.com.kproj.salesman.negotiation2.domain.model.negotiation.SaleableItem;
import br.com.kproj.salesman.negotiation2.domain.model.negotiation.Temperature;
import br.com.kproj.salesman.negotiation2.domain.model.operationregion.OperationRegion;
import br.com.kproj.salesman.negotiation2.domain.model.payment.InstallmentItem;
import br.com.kproj.salesman.negotiation2.domain.model.seller.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class BusinessProposalEntityToNegotiationConverter implements Converter<BusinessProposalEntity, Negotiation> {

    @Autowired
    private OperationRegionEntityToOperationRegionConverter regionConverter;

    @Autowired
    private UserEntityToSellerConverter userEntityConverter;

    @Autowired
    private ProposalItemEntityToSaleableItemConverter proposalItemConverter;

    @Autowired
    private PaymentEntityToInstallmentConverter paymentConverter;

    @Override
    public Negotiation convert(BusinessProposalEntity proposalEntity, Object... args) {

        NegotiationBuilder negotiationBuilder = NegotiationBuilder.createNegotiation(proposalEntity.getId())
                .withCareOf(proposalEntity.getCareOf())
                .withDeliveryForeCast(proposalEntity.getDeliveryForeCast())
                .withIntroduction(proposalEntity.getIntroduction());

        OperationRegion operationRegion = regionConverter.convert(proposalEntity.getOperationRegionEntity());
        Seller seller = userEntityConverter.convert(proposalEntity.getSeller());
        List<SaleableItem> saleableItems = proposalItemConverter.convert(proposalEntity.getSaleableItems());
        List<InstallmentItem> installmentItems = paymentConverter.convert(proposalEntity.getPaymentItems());

        negotiationBuilder.withOperation(operationRegion);
        negotiationBuilder.withSeller(seller);
        negotiationBuilder.withSaleableItem(saleableItems);
        negotiationBuilder.withInstallments(installmentItems);
        negotiationBuilder.withTemperature(Temperature.valueOf(proposalEntity.getTemperature().name()));

        return negotiationBuilder.build();
    }
}
