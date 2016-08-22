package br.com.kproj.salesman.products_catalog.infrastructure.persistence;

import br.com.kproj.salesman.infrastructure.entity.saleable.ProductEntity;
import br.com.kproj.salesman.infrastructure.entity.saleable.SalePackageEntity;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.infrastructure.entity.saleable.ServiceEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.SaleableUnit;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.SaleableUnitRepository;
import br.com.kproj.salesman.products_catalog.infrastructure.persistence.springdata.SaleableUnitRepositorySpringData;
import br.com.kproj.salesman.products_catalog.infrastructure.persistence.translate.ProductEntityToProductConverter;
import br.com.kproj.salesman.products_catalog.infrastructure.persistence.translate.SalePackageEntityToSalePackageConverter;
import br.com.kproj.salesman.products_catalog.infrastructure.persistence.translate.ServiceEntityToServiceConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class SaleableUnitRepositoryHibernate extends BaseRespositoryImpl<SaleableUnit, SaleableUnitEntity> implements SaleableUnitRepository {

    private final Map<Class<?>, SelectConverter> poolConverters = new HashMap<>();

    @Autowired
    private SaleableUnitRepositorySpringData repository;

    @Autowired
    private SalePackageEntityToSalePackageConverter packageConverter;
    @Autowired
    private ProductEntityToProductConverter productConverter;
    @Autowired
    private ServiceEntityToServiceConverter serviceConverter;

    {
        poolConverters.put(ProductEntity.class, entity -> productConverter.convert((ProductEntity) entity));
        poolConverters.put(ServiceEntity.class, entity -> serviceConverter.convert((ServiceEntity) entity));
        poolConverters.put(SalePackageEntity.class, entity -> packageConverter.convert((SalePackageEntity) entity));
    }


    @Override
    public Page<SaleableUnit> findAll(Pageable page) {
        Page<SaleableUnitEntity> saleableUnits = repository.findAll(page);

        List<SaleableUnit> converteds = saleableUnits.getContent().stream()
                .map(item -> {
                    if (poolConverters.containsKey(item.getClass())) {
                        return poolConverters.get(item.getClass()).select(item);
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
            SaleableUnit saleableFound = poolConverters.get(result.get().getClass()).select(result.get());
            return Optional.of(saleableFound);
        }

    }


    @Override
    public BaseRepositoryLegacy<SaleableUnitEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<SaleableUnitEntity, SaleableUnit> getConverter() {
        return null;
    }

    private interface SelectConverter<T extends SaleableUnit> {

        T select(SaleableUnitEntity entity);
    }
}
