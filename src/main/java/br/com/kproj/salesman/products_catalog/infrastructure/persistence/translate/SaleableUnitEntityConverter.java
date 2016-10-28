package br.com.kproj.salesman.products_catalog.infrastructure.persistence.translate;

import br.com.kproj.salesman.infrastructure.entity.saleable.SalePackageEntity;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.SaleableUnit;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.salepackage.SalePackage;
import org.springframework.stereotype.Component;


@Component
public class SaleableUnitEntityConverter implements Converter<SaleableUnitEntity, SaleableUnit> {





    @Override
    public SaleableUnit convert(SaleableUnitEntity saleableUnitEntity, Object... args) {
//        SaleableUnit saleableUnit = new SaleableUnit()
//        saleableUnit.setId(saleableUnitEntity.getId());
//        saleableUnit.setName(saleableUnitEntity.getName());
//        saleableUnit.setActive(saleableUnitEntity.getActive());
//        saleableUnit.setDescription(saleableUnitEntity.getDescription());
//        saleableUnit.setPrice(saleableUnitEntity.getPrice());
//        saleableUnit.setPriceCost(saleableUnitEntity.getPriceCost());
        return null;
    }
}
