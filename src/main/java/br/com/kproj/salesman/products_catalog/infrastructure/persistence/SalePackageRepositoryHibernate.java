package br.com.kproj.salesman.products_catalog.infrastructure.persistence;

import br.com.kproj.salesman.infrastructure.entity.saleable.SalePackageEntity;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.infrastructure.helpers.AutowireHelper;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.SaleableUnit;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.salepackage.SalePackage;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.salepackage.SalePackageRepository;
import br.com.kproj.salesman.products_catalog.infrastructure.persistence.springdata.SalePackageRepositorySpringData;
import br.com.kproj.salesman.products_catalog.infrastructure.persistence.translate.SalePackageEntityToSalePackageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.helpers.AutowireHelper.autowire;

@Repository
public class SalePackageRepositoryHibernate extends BaseRespositoryImpl<SalePackage, SalePackageEntity> implements SalePackageRepository {

    @Autowired
    private SalePackageRepositorySpringData repository;

    @Autowired
    private SalePackageEntityToSalePackageConverter converter;


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
        result.get().removeSaleableUnit(new SaleableUnitEntity(salePackage.getId()));
    }

    @Override
    public void addSaleable(SalePackage salePackage, SaleableUnit saleable) {
        Optional<SalePackageEntity> result = repository.getOne(salePackage.getId());
        result.get().addSaleableUnit(new SaleableUnitEntity(salePackage.getId()));
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
            return Optional.of(converter.convert(result.get()));
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
