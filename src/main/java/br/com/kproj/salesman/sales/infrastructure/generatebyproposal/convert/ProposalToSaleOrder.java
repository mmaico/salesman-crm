package br.com.kproj.salesman.sales.infrastructure.generatebyproposal.convert;


import br.com.kproj.salesman.infrastructure.entity.builders.SalesOrderBuilder;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProposalToSaleOrder implements Converter<BusinessProposalEntity, SalesOrder> {

    @Autowired
    private ProposalPaymentToSalesOrderPayment paymentConverter;

    @Autowired
    private ProposalSaleableItemToSalesOrderItem orderItemConverter;

    @Override
    public SalesOrder convert(BusinessProposalEntity source) {

        SalesOrderBuilder builder = SalesOrderBuilder.createSalesOrder()
                .withClient(source.getClient())
                .withProposal(source)
                .withDeliveryForeCast(source.getDeliveryForeCast())
                .withOperationRegion(source.getOperationRegionEntity())
                .withCurrentDate()
                .withSeller(source.getSeller());


        source.getPaymentItems().stream()
                .forEach(payment -> builder.addPayment(paymentConverter.convert(payment)));

        source.getSaleableItems().stream()
                .forEach(salesItem -> builder.addSalesOrderItem(orderItemConverter.convert(salesItem)));

        return builder.build();
    }
}
