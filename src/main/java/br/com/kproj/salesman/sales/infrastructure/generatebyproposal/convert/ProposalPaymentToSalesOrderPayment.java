package br.com.kproj.salesman.sales.infrastructure.generatebyproposal.convert;

import br.com.kproj.salesman.infrastructure.entity.builders.SalesOrderPaymentItemBuilder;
import br.com.kproj.salesman.infrastructure.entity.proposal.ProposalPaymentItem;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrderPaymentItem;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProposalPaymentToSalesOrderPayment implements Converter<ProposalPaymentItem, SalesOrderPaymentItem> {


    @Override
    public SalesOrderPaymentItem convert(ProposalPaymentItem source) {

        return SalesOrderPaymentItemBuilder.createSalesOrderPaymentItem()
                    .withDueDate(source.getDueDate())
                    .withObservation(source.getObservation())
                    .withValue(source.getValue()).build();

    }
}
