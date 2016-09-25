package br.com.kproj.salesman.sales.infrastructure.generatesale.convert;


import br.com.kproj.salesman.infrastructure.entity.OperationRegionEntity;
import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.builders.SalesOrderBuilder;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrderEntity;
import br.com.kproj.salesman.sales.view.dtos.negotiation.NegotiationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NegotiationToSaleOrder implements Converter<NegotiationDTO, SalesOrderEntity> {

    @Autowired
    private InstallmentToSalesOrderPayment paymentConverter;

    @Autowired
    private ProposalSaleableItemToSalesOrderItem orderItemConverter;

    @Override
    public SalesOrderEntity convert(NegotiationDTO source) {

        SalesOrderBuilder builder = SalesOrderBuilder.createSalesOrder()
                //.withClient(source.getClient())
                .withProposal(new BusinessProposalEntity(source.getId()))
                .withDeliveryForeCast(source.getDeliveryForeCast())
                .withOperationRegion(new OperationRegionEntity(source.getRegion().getId()))
                .withCurrentDate()
                .withSeller(new UserEntity(source.getSeller().getId()));

        source.getInstallments().stream()
                .forEach(installment -> builder.addPayment(paymentConverter.convert(installment)));

        source.getSaleablesItems().stream()
                .forEach(saleableItem -> builder.addSalesOrderItem(orderItemConverter.convert(saleableItem)));

        return builder.build();
    }
}
