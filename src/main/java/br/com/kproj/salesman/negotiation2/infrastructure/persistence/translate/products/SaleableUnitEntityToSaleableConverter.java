package br.com.kproj.salesman.negotiation2.infrastructure.persistence.translate.products;

import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.negotiation2.domain.model.product.Saleable;
import org.springframework.stereotype.Component;

import static br.com.kproj.salesman.negotiation2.domain.model.product.SaleableBuilder.createSaleable;

@Component
public class SaleableUnitEntityToSaleableConverter implements Converter<SaleableUnitEntity, Saleable> {

    @Override
    public Saleable convert(SaleableUnitEntity entity, Object... args) {

        return createSaleable(entity.getId()).build();
    }
}
