package br.com.kproj.salesman.products_catalog.catalog.infrastructure.persistence;

import br.com.kproj.salesman.infrastructure.entity.saleable.SalePackageEntity;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.products_catalog.catalog.domain.model.saleables.Represent;
import br.com.kproj.salesman.products_catalog.catalog.domain.model.saleables.SaleableUnit;
import br.com.kproj.salesman.products_catalog.catalog.domain.model.salepackage.SalePackage;
import br.com.kproj.salesman.products_catalog.catalog.domain.model.salepackage.SalePackageRepository;
import br.com.kproj.salesman.products_catalog.catalog.infrastructure.persistence.springdata.SalePackageRepositorySpringData;
import br.com.kproj.salesman.products_catalog.catalog.infrastructure.persistence.springdata.SaleableUnitRepositorySpringData;
import br.com.kproj.salesman.products_catalog.catalog.infrastructure.persistence.translate.SalePackageEntityToSalePackageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.helpers.AutowireHelper.autowire;
import static com.trex.clone.BusinessModelClone.from;

@Repository
public class SalePackageRepositoryHibernate extends BaseRespositoryImpl<SalePackage, SalePackageEntity> implements SalePackageRepository {

    @Autowired
    private SalePackageRepositorySpringData repository;

    @Autowired
    private SalePackageEntityToSalePackageConverter converter;

    @Autowired
    private SaleableUnitRepositorySpringData saleableRepository;

    public Optional<SalePackage> save(SalePackage salePackage) {

        Optional<SalePackageEntity> salePackageEntity = repository.getOne(salePackage.getId());

        if (salePackageEntity.isPresent()) {
            from(salePackage).merge(salePackageEntity.get());
            return Optional.of(getConverter().convert(salePackageEntity.get()));
        } else {
            SalePackageEntity newPackage = from(salePackage).convertTo(SalePackageEntity.class);
            newPackage.setSaleable(new SaleableUnitEntity(salePackage.getId()));
            SalePackageEntity entity = repository.save(newPackage);

            saleableRepository.represent(entity.getId(), Represent.PACKAGE);

            return Optional.ofNullable(getConverter().convert(entity));
        }
    }

    @Override
    public Optional<SalePackage> findBySaleable(SalePackage salePackage, SaleableUnit saleable) {
        SalePackageEntity salePackageEntity = new SalePackageEntity(salePackage.getId());
        SaleableUnitEntity saleableUnitEntity = new SaleableUnitEntity(saleable.getId());

        Optional<SalePackageEntity> salePackages = repository.findBySaleable(salePackageEntity, saleableUnitEntity);

        if (!salePackages.isPresent()) {
            return Optional.empty();
        } else {
            return Optional.of(converter.convert(salePackages.get()));
        }
    }

    @Override
    public void removeSaleable(SalePackage salePackage, SaleableUnit saleable) {
        Optional<SalePackageEntity> result = repository.getOne(salePackage.getId());
        result.get().removeSaleableUnit(new SaleableUnitEntity(saleable.getId()));
        repository.save(result.get());
    }

    @Override
    public void addSaleable(SalePackage salePackage, SaleableUnit saleable) {
        Optional<SalePackageEntity> result = repository.getOne(salePackage.getId());
        result.get().addSaleableUnit(new SaleableUnitEntity(saleable.getId()));
        repository.save(result.get());
    }

    public Page<SalePackage> findAll(Pageable page) {
        Page<SalePackageEntity> salePackageEntities = getRepository().findAll(page);

        List<SalePackage> converteds = salePackageEntities.getContent().stream()
                .map(item -> autowire(converter.convert(item)))
                .collect(Collectors.toList());

        return new PageImpl<>(converteds, page, salePackageEntities.getTotalElements());
    }

    @Override
    public Optional<SalePackage> findOne(Long id) {
        Optional<SalePackageEntity> result = repository.getOne(id);

        if (!result.isPresent()) {
            return Optional.empty();
        } else {
            return Optional.of(getConverter().convert(result.get()));
        }

    }


    @Override
    public BaseRepositoryLegacy<SalePackageEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<SalePackageEntity, SalePackage> getConverter() {
        return converter;
    }

}
