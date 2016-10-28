package br.com.kproj.salesman.products_catalog.infrastructure.persistence.translate;

import br.com.kproj.salesman.infrastructure.entity.saleable.ProductEntity;
import br.com.kproj.salesman.infrastructure.entity.saleable.SalePackageEntity;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.infrastructure.entity.saleable.ServiceEntity;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.SaleableUnit;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.salepackage.SalePackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Component
public class SalePackageEntityToSalePackageConverter implements Converter<SalePackageEntity, SalePackage> {

    private final Map<Class<?>, SelectConverter> poolConverters = new HashMap<>();

    @Autowired
    private SaleableUnitEntityConverter saleableUnitConverter;

    @Autowired
    private ProductEntityToProductConverter productConverter;

    @Autowired
    private ServiceEntityToServiceConverter serviceConverter;

    {
//        poolConverters.put(ProductEntity.class, entity -> productConverter.convert((ProductEntity) entity));
//        poolConverters.put(ServiceEntity.class, entity -> serviceConverter.convert((ServiceEntity) entity));
    }



    @Override
    public SalePackage convert(SalePackageEntity salePackageEntity, Object... args) {
        SalePackage salePackage = new SalePackage();

        //saleableUnitConverter.convert(salePackageEntity, salePackage);

        List<SaleableUnit> result = salePackageEntity.getSaleableUnits().stream()
                .map(item -> {
                    if (poolConverters.containsKey(item.getClass())) {
                        return poolConverters.get(item.getClass()).select(item);
                    } else {
                        throw new IllegalArgumentException("Invalid type on saleables in package:" + salePackage);
                    }
                }).collect(Collectors.toList());

        salePackage.setSaleables(result);

        return salePackage;
    }

    private interface SelectConverter<T extends SaleableUnit> {

        T select(SaleableUnitEntity entity);
    }
}
