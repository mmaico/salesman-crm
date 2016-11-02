package br.com.kproj.salesman.products_catalog.infrastructure.persistence.translate;

import br.com.kproj.salesman.infrastructure.entity.saleable.SalePackageEntity;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.SaleableUnit;
import br.com.kproj.salesman.products_catalog.domain.model.salepackage.SalePackage;
import com.trex.clone.BusinessModelClone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Component
public class SalePackageEntityToSalePackageConverter implements Converter<SalePackageEntity, SalePackage> {

    @Autowired
    private SaleableUnitEntityConverter saleableConverter;

    @Override
    public SalePackage convert(SalePackageEntity salePackageEntity, Object... args) {

        SalePackage salePackage = BusinessModelClone.from(salePackageEntity).convertTo(SalePackage.class);
        BusinessModelClone.from(salePackageEntity.getSaleable()).merge(salePackage);

        List<SaleableUnit> saleablesInPackage = salePackageEntity.getSaleableUnits().stream()
                .map(entity -> saleableConverter.convert(entity))
                .collect(Collectors.toList());

        salePackage.setSaleables(saleablesInPackage);

        return salePackage;
    }

}
