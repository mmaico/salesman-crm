package br.com.kproj.salesman.products_catalog.catalog.infrastructure.persistence;

import br.com.kproj.salesman.infrastructure.entity.saleable.MeasurementUnitEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.products_catalog.catalog.domain.model.unit.Unit;
import br.com.kproj.salesman.products_catalog.catalog.domain.model.unit.UnitRepository;
import br.com.kproj.salesman.products_catalog.catalog.infrastructure.persistence.springdata.UnitRepositorySpringData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UnitRepositoryHibernate extends BaseRespositoryImpl<Unit, MeasurementUnitEntity> implements UnitRepository {

    @Autowired
    private UnitRepositorySpringData repository;


    @Override
    public BaseRepositoryLegacy<MeasurementUnitEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<MeasurementUnitEntity, Unit> getConverter() {
        return ((entity, args) -> new Unit(entity.getId(), entity.getName()));
    }

}
