package br.com.kproj.salesman.sales.infrastructure.generatesale.convert;

import br.com.kproj.salesman.infrastructure.entity.builders.SalesOrderPaymentItemBuilder;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrderPaymentItem;
import br.com.kproj.salesman.sales.application.dto.InstallmentItemDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class InstallmentToSalesOrderPayment implements Converter<InstallmentItemDTO, SalesOrderPaymentItem> {


    @Override
    public SalesOrderPaymentItem convert(InstallmentItemDTO source) {

        return SalesOrderPaymentItemBuilder.createSalesOrderPaymentItem()
                    .withDueDate(source.getDueDate())
                    .withObservation(source.getObservation())
                    .withValue(source.getValue()).build();

    }
}
