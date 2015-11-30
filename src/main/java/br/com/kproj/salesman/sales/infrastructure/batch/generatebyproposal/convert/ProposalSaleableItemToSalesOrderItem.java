package br.com.kproj.salesman.sales.infrastructure.batch.generatebyproposal.convert;

import br.com.kproj.salesman.infrastructure.entity.builders.SalesOrderItemBuilder;
import br.com.kproj.salesman.infrastructure.entity.proposal.ProposalSaleableItem;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrderItem;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProposalSaleableItemToSalesOrderItem implements Converter<ProposalSaleableItem, SalesOrderItem> {


    @Override
    public SalesOrderItem convert(ProposalSaleableItem source) {

        return SalesOrderItemBuilder.createSalesOrderItem()
                    .withOriginalPrice(source.getOriginalPrice())
                .withPrice(source.getPrice())
                .withQuantity(source.getQuantity())
                .withSaleable(source.getSaleableUnit())
                .withSalesPackage(source.getSalePackage()).build();

    }
}
