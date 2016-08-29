package br.com.kproj.salesman.negotiation.infrastructure.persistence.translate.products;

import br.com.kproj.salesman.infrastructure.entity.saleable.SalePackageEntity;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.negotiation.domain.model.product.SaleablePackage;
import br.com.kproj.salesman.negotiation.domain.model.product.SaleablePackageBuilder;
import org.springframework.stereotype.Component;

import static br.com.kproj.salesman.negotiation.domain.model.product.SaleableBuilder.createSaleable;

@Component
public class PackageToSaleableConverter implements Converter<SalePackageEntity, SaleablePackage> {

    @Override
    public SaleablePackage convert(SalePackageEntity entity, Object... args) {
        SaleablePackageBuilder aPackage = SaleablePackageBuilder.createPackage(entity.getId());

        entity.getSaleableUnits().forEach(product ->
                aPackage.addSaleable(createSaleable(product.getId()).build()));

        return aPackage.build();
    }
}
