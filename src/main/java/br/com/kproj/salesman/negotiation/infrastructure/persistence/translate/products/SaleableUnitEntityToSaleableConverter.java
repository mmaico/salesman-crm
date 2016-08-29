package br.com.kproj.salesman.negotiation.infrastructure.persistence.translate.products;

import br.com.kproj.salesman.infrastructure.entity.saleable.SalePackageEntity;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.negotiation.domain.model.product.Saleable;
import br.com.kproj.salesman.negotiation.domain.model.product.SaleablePackageBuilder;
import org.springframework.stereotype.Component;

import static br.com.kproj.salesman.infrastructure.entity.saleable.SaleableTypeEntity.PRODUCT;
import static br.com.kproj.salesman.infrastructure.entity.saleable.SaleableTypeEntity.SERVICE;
import static br.com.kproj.salesman.negotiation.domain.model.product.SaleableBuilder.createSaleable;

@Component
public class SaleableUnitEntityToSaleableConverter implements Converter<SaleableUnitEntity, Saleable> {

    @Override
    public Saleable convert(SaleableUnitEntity entity, Object... args) {

        if (PRODUCT.equals(entity.getType()) || SERVICE.equals(entity.getType())) {
            return createSaleable(entity.getId()).withPrice(entity.getPrice()).build();
        } else {
            SalePackageEntity salePackageEntity = (SalePackageEntity) entity;

            SaleablePackageBuilder packageBuilder = SaleablePackageBuilder.createPackage(salePackageEntity.getId());
            packageBuilder.withPrice(salePackageEntity.getPrice());

            salePackageEntity.getSaleableUnits().forEach(item ->
                packageBuilder.addSaleable(createSaleable(entity.getId()).withPrice(item.getPrice()).build())
            );

            return packageBuilder.build();
        }

    }
}
