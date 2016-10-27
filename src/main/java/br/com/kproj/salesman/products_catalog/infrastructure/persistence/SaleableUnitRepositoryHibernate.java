package br.com.kproj.salesman.products_catalog.infrastructure.persistence;

import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableTypeEntity;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.SaleableUnit;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.SaleableUnitRepository;
import br.com.kproj.salesman.products_catalog.infrastructure.configuration.SaleableUnitSpecializedSupport;
import br.com.kproj.salesman.products_catalog.infrastructure.persistence.springdata.SaleableUnitRepositorySpringData;
import br.com.kproj.salesman.products_catalog.infrastructure.persistence.support.SaleableUtils;
import br.com.kproj.salesman.products_catalog.infrastructure.persistence.translate.SaleableUnitEntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.products_catalog.infrastructure.persistence.support.SaleableUtils.getType;
import static com.trex.clone.BusinessModelClone.from;

@Repository
public class SaleableUnitRepositoryHibernate implements SaleableUnitRepository {

    @Resource(name="saleablesConverters")
    Map<SaleableTypeEntity, SaleableUnitSpecializedSupport.SelectConverter> converters;

    @Resource(name="saleablesRepositories")
    Map<SaleableTypeEntity, SaleableUnitSpecializedSupport.PersistRepository> repositories;

    @Autowired
    private SaleableUnitRepositorySpringData repository;

    @Autowired
    private SaleableUnitEntityConverter converter;


    @Override
    public Page<SaleableUnit> findAll(Pageable page) {
        Page<SaleableUnitEntity> saleableUnits = repository.findAll(page);

        List<SaleableUnit> converteds = saleableUnits.getContent().stream()
                .map(item -> {
                    if (converters.containsKey(item.getType())) {
                        return converters.get(item.getType()).select(item);
                    } else {
                        throw new IllegalArgumentException("Invalid type on saleables:" + item);
                    }
                }).collect(Collectors.toList());

        return new PageImpl<>(converteds, page, saleableUnits.getTotalElements());
    }

    @Override
    public Optional<SaleableUnit> findOne(Long id) {
        Optional<SaleableUnitEntity> result = Optional.ofNullable(repository.findOne(id));

        if (!result.isPresent()) {
            return Optional.empty();
        } else {
            SaleableUnit saleableFound = converters.get(result.get().getType()).select(result.get());
            return Optional.of(saleableFound);
        }

    }

    @Override
    public Optional<SaleableUnit> save(SaleableUnit saleableUnit) {

        if (saleableUnit.isNew()) {
            SaleableUnitEntity resultEntity = from(saleableUnit).convertTo(SaleableUnitEntity.class);
            SaleableUnitEntity saleableSaved = repository.save(resultEntity);
            SaleableUnit saleable = new SaleableUnit();
            converter.convert(saleableSaved, saleable);

            return Optional.ofNullable(saleable);
        } else {
            Optional<SaleableUnitEntity> productEntity = Optional.ofNullable(repository.findOne(saleableUnit.getId()));
            from(saleableUnit).merge(productEntity.get());
            SaleableUnit valueConverted = converters.get(productEntity.get().getType()).select(productEntity.get());

            return Optional.ofNullable(valueConverted);
        }
    }

}
