package br.com.kproj.salesman.products_catalog.catalog.view.support.builders;


import br.com.kproj.salesman.products_catalog.catalog.domain.model.products.Product;
import br.com.kproj.salesman.products_catalog.catalog.domain.model.saleables.SaleableUnit;
import br.com.kproj.salesman.products_catalog.catalog.domain.model.services.Service;
import br.com.kproj.salesman.products_catalog.catalog.domain.model.salepackage.SalePackage;
import br.com.kproj.salesman.products_catalog.catalog.domain.model.unit.Unit;
import br.com.kproj.salesman.products_catalog.catalog.view.support.SaleableType;
import br.com.kproj.salesman.products_catalog.catalog.view.support.dtos.SaleableDTO;

import java.util.HashMap;
import java.util.Map;

import static br.com.kproj.salesman.products_catalog.catalog.view.support.SaleableType.*;

public class SaleableStrategyBuilder {

    private Map<SaleableType, Convert<? extends SaleableUnit>> converters = new HashMap<>();

    {
        converters.put(PRODUCT, saleableDTO -> {
            Product product = new Product();
            SaleableMerge.merge(saleableDTO, product);
            product.setUnit(new Unit(saleableDTO.getUnit().getId()));

            return product;
        });

        converters.put(SERVICE, saleableDTO -> {
            Service service = new Service();
            SaleableMerge.merge(saleableDTO, service);

            return service;
        });

        converters.put(SALE_PACKAGE, saleableDTO -> {
            SalePackage salePackage = new SalePackage();
            SaleableMerge.merge(saleableDTO, salePackage);

            return salePackage;
        });
    }

    public static <T extends SaleableUnit> T build(SaleableDTO saleableDTO) {
        SaleableType type = SaleableType.get(saleableDTO.getType());
        return (T) new SaleableStrategyBuilder().getConverters().get(type).convert(saleableDTO);
    }

    public Map<SaleableType, Convert<? extends SaleableUnit>> getConverters() {
        return converters;
    }

    private interface Convert <T> {
        T convert(SaleableDTO saleableDTO);
    }
}

