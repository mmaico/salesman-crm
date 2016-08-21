package br.com.kproj.salesman.products_catalog.infrastructure.persistence.translate;

import br.com.kproj.salesman.infrastructure.entity.saleable.MeasurementUnitEntity;
import br.com.kproj.salesman.infrastructure.entity.saleable.ProductEntity;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.Product;
import br.com.kproj.salesman.products_catalog.domain.model.unit.Unit;
import org.springframework.stereotype.Component;


@Component
public class MeasurementUnitEntityToUnitConverter implements Converter<MeasurementUnitEntity, Unit> {

    @Override
    public Unit convert(MeasurementUnitEntity measurementUnitEntity, Object... args) {

        Unit unit = new Unit();

        if (measurementUnitEntity == null) {
            return unit;
        }
        unit.setId(measurementUnitEntity.getId());
        unit.setName(measurementUnitEntity.getName());

        return unit;
    }
}
