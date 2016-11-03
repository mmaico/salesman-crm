package br.com.kproj.salesman.products_catalog.catalog.infrastructure.persistence.translate;

import br.com.kproj.salesman.infrastructure.entity.saleable.MeasurementUnitEntity;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.products_catalog.catalog.domain.model.unit.Unit;
import br.com.kproj.salesman.products_catalog.catalog.infrastructure.persistence.springdata.UnitRepositorySpringData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MeasurementUnitEntityToUnitConverter implements Converter<MeasurementUnitEntity, Unit> {

    private UnitRepositorySpringData unitRepository;

    @Autowired
    public MeasurementUnitEntityToUnitConverter(UnitRepositorySpringData unitRepository) {
        this.unitRepository = unitRepository;
    }

    @Override
    public Unit convert(MeasurementUnitEntity measurementUnitEntity, Object... args) {

        Unit unit = new Unit();

        if (measurementUnitEntity == null) {
            return unit;
        }

        MeasurementUnitEntity entity = unitRepository.findOne(measurementUnitEntity.getId());

        unit.setId(entity.getId());
        unit.setName(entity.getName());

        return unit;
    }
}
