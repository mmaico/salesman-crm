package br.com.kproj.salesman.products_catalog.catalog.infrastructure.persistence.springdata.impl;

import br.com.kproj.salesman.infrastructure.configuration.ServiceLocator;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableTypeEntity;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.products_catalog.catalog.domain.model.saleables.Represent;
import br.com.kproj.salesman.products_catalog.catalog.infrastructure.persistence.springdata.SaleableUnitRepositorySpringData;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Stream;

@Repository
public class SaleableUnitRepositorySpringDataImpl implements SaleableUnitCustomRepository {

    @Override
    public void represent(Long id, Represent represent) {
        SaleableUnitRepositorySpringData repository = ServiceLocator.getBean(SaleableUnitRepositorySpringData.class);
        SaleableUnitEntity entity = repository.findOne(id);

        Optional<SaleableTypeEntity> result = Stream.of(SaleableTypeEntity.values())
                .filter(item -> item.name().equals(represent.name())).findFirst();

        entity.setType(result.orElse(null));
    }
}
