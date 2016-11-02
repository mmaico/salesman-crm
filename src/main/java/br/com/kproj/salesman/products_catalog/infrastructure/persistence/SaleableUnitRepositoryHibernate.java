package br.com.kproj.salesman.products_catalog.infrastructure.persistence;

import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableTypeEntity;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.Represent;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.SaleableUnit;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.SaleableUnitRepository;
import br.com.kproj.salesman.products_catalog.infrastructure.persistence.springdata.SaleableUnitRepositorySpringData;
import br.com.kproj.salesman.products_catalog.infrastructure.persistence.translate.SaleableUnitEntityConverter;
import com.trex.clone.BusinessModelClone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.trex.clone.BusinessModelClone.from;

@Repository
public class SaleableUnitRepositoryHibernate implements SaleableUnitRepository {

    private SaleableUnitRepositorySpringData repository;
    private SaleableUnitEntityConverter converter;

    @Autowired
    public SaleableUnitRepositoryHibernate (SaleableUnitRepositorySpringData repository, SaleableUnitEntityConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public Page<SaleableUnit> findAll(Pageable page) {
        Page<SaleableUnitEntity> saleableUnits = repository.findAll(page);

        List<SaleableUnit> converteds = saleableUnits.getContent().stream()
                .map(item -> convert(item).get()).collect(Collectors.toList());

        return new PageImpl<>(converteds, page, saleableUnits.getTotalElements());
    }

    @Override
    public Optional<SaleableUnit> findOne(Long id) {
        Optional<SaleableUnitEntity> result = Optional.ofNullable(repository.findOne(id));

        if (!result.isPresent()) {
            return Optional.empty();
        } else {
            return convert(result.get());
        }
    }

    @Override
    public Optional<SaleableUnit> save(SaleableUnit saleableUnit) {

        if (saleableUnit.isNew()) {
            SaleableUnitEntity resultEntity = from(saleableUnit).convertTo(SaleableUnitEntity.class);
            SaleableUnitEntity saleableSaved = repository.save(resultEntity);
            return convert(saleableSaved);
        } else {
            Optional<SaleableUnitEntity> productEntity = Optional.ofNullable(repository.findOne(saleableUnit.getId()));
            from(saleableUnit).merge(productEntity.get());
            return convert(productEntity.get());
        }
    }

    private Optional<SaleableUnit> convert(SaleableUnitEntity saleableUnitEntity) {
        SaleableUnit saleableUnit = BusinessModelClone.from(saleableUnitEntity).convertTo(SaleableUnit.class);
        Optional<Represent> represent = saleableUnitEntity.getType() == null
                ? Optional.of(Represent.NO_REPRESENT)
                : Optional.ofNullable(Represent.valueOf(saleableUnitEntity.getType().name()));

        saleableUnit.setRepresent(represent.orElse(Represent.NO_REPRESENT));

        return Optional.ofNullable(saleableUnit);
    }
}
