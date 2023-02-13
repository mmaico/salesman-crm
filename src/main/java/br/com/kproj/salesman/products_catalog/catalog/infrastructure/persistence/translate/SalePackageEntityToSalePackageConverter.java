package br.com.kproj.salesman.products_catalog.catalog.infrastructure.persistence.translate;

import br.com.kproj.salesman.infrastructure.entity.saleable.SalePackageEntity;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.products_catalog.catalog.domain.model.salepackage.SalePackage;
import br.com.kproj.salesman.products_catalog.catalog.domain.model.salepackage.SaleableRelation;
import com.github.mmaico.clone.BusinessModelClone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class SalePackageEntityToSalePackageConverter implements Converter<SalePackageEntity, SalePackage> {

    @Autowired
    private SaleableUnitEntityConverter saleableConverter;

    @Override
    public SalePackage convert(SalePackageEntity salePackageEntity, Object... args) {

        SalePackage salePackage = BusinessModelClone.from(salePackageEntity).convertTo(SalePackage.class);
        BusinessModelClone.from(salePackageEntity.getSaleable()).merge(salePackage);

        List<SaleableRelation> relations = salePackageEntity.getRelations().stream().map(relationEntity -> {
            SaleableRelation relation = new SaleableRelation();
            relation.setId(relationEntity.getId());
            relation.setSaleable(saleableConverter.convert(relationEntity.getSaleable()));
            return relation;
        }).collect(Collectors.toList());

        salePackage.setRelations(relations);

        return salePackage;
    }

}
