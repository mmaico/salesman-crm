package br.com.kproj.salesman.products_catalog.catalog.infrastructure.persistence.translate;

import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.products_catalog.catalog.domain.model.saleables.Represent;
import br.com.kproj.salesman.products_catalog.catalog.domain.model.saleables.SaleableUnit;
import com.trex.clone.BusinessModelClone;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class SaleableUnitEntityConverter {


    public SaleableUnit convert(SaleableUnitEntity saleableUnitEntity) {
        SaleableUnit saleableUnit = BusinessModelClone.from(saleableUnitEntity).convertTo(SaleableUnit.class);
        Optional<Represent> represent = saleableUnitEntity.getType() == null
                ? Optional.of(Represent.NO_REPRESENT)
                : Optional.ofNullable(Represent.valueOf(saleableUnitEntity.getType().name()));

        saleableUnit.setRepresent(represent.orElse(Represent.NO_REPRESENT));

        return saleableUnit;
    }

}
