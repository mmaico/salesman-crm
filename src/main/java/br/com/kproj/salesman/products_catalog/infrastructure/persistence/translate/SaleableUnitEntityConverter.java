package br.com.kproj.salesman.products_catalog.infrastructure.persistence.translate;

import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.SaleableUnit;
import org.springframework.stereotype.Component;


@Component
public class SaleableUnitEntityConverter <T extends SaleableUnitEntity, Y extends SaleableUnit>  {


    public void convert(T saleableUnitEntity, Y saleableUnit) {
        saleableUnit.setId(saleableUnitEntity.getId());
        saleableUnit.setName(saleableUnitEntity.getName());
        saleableUnit.setActive(saleableUnitEntity.getActive());
        saleableUnit.setDescription(saleableUnitEntity.getDescription());
        saleableUnit.setPrice(saleableUnitEntity.getPrice());
        saleableUnit.setPriceCost(saleableUnitEntity.getPriceCost());
    }


}
