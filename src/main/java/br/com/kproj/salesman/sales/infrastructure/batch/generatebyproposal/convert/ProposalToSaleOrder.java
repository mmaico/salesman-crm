package br.com.kproj.salesman.sales.infrastructure.batch.generatebyproposal.convert;


import br.com.kproj.salesman.infrastructure.entity.builders.SalesOrderBuilder;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProposalToSaleOrder implements Converter<BusinessProposal, SalesOrder> {

    @Autowired
    private ProposalPaymentToSalesOrderPayment paymentConverter;

    @Autowired
    private ProposalSaleableItemToSalesOrderItem orderItemConverter;

    @Override
    public SalesOrder convert(BusinessProposal source) {

        SalesOrderBuilder builder = SalesOrderBuilder.createSalesOrder()
                .withClient(source.getClient())
                .withDeliveryForeCast(source.getDeliveryForeCast())
                .withOperationRegion(source.getOperationRegion())
                .withVendor(source.getVendor());


        source.getPaymentItems().stream()
                .forEach(payment -> builder.addPayment(paymentConverter.convert(payment)));

        source.getSaleableItems().stream()
                .forEach(salesItem -> builder.addSalesOrderItem(orderItemConverter.convert(salesItem)));


        return builder.build();
    }
}
