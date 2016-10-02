package br.com.kproj.salesman.sales.infrastructure.generatesale.convert;

import br.com.kproj.salesman.infrastructure.entity.builders.SalesOrderItemBuilder;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrderItem;
import br.com.kproj.salesman.infrastructure.entity.saleable.SalePackageEntity;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.sales.application.dto.SaleableItemDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProposalSaleableItemToSalesOrderItem implements Converter<SaleableItemDTO, SalesOrderItem> {


    @Override
    public SalesOrderItem convert(SaleableItemDTO source) {
        SalesOrderItemBuilder builder = SalesOrderItemBuilder.createSalesOrderItem()
                .withOriginalPrice(source.getOriginalPrice())
                .withPrice(source.getPrice())
                .withQuantity(source.getQuantity());

        if (source.getSaleable() != null) {
            builder.withSaleable(new SaleableUnitEntity(source.getSaleable().getId()));
        }

        if (source.getSaleablePackage() != null) {
            builder.withSalesPackage(new SalePackageEntity(source.getSaleablePackage().getId()));
        }

        return builder.build();

    }
}
