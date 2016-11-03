package br.com.kproj.salesman.products_catalog.catalog.infrastructure.persistence.support;


import br.com.kproj.salesman.infrastructure.entity.saleable.*;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.products_catalog.catalog.domain.model.products.Product;
import br.com.kproj.salesman.products_catalog.catalog.domain.model.saleables.SaleableUnit;
import br.com.kproj.salesman.products_catalog.catalog.domain.model.services.Service;
import br.com.kproj.salesman.products_catalog.catalog.domain.model.salepackage.SalePackage;

import static br.com.kproj.salesman.infrastructure.entity.saleable.SaleableTypeEntity.PRODUCT;
import static br.com.kproj.salesman.infrastructure.entity.saleable.SaleableTypeEntity.SERVICE;

public class SaleableUtils {

    public static SaleableTypeEntity getType(SaleableUnit saleableUnit) {
        if (saleableUnit instanceof Product) {
            return PRODUCT;
        } else if (saleableUnit instanceof Service) {
            return SERVICE;
        } else if (saleableUnit instanceof SalePackage) {
            return SaleableTypeEntity.PACKAGE;
        }

        throw ValidationException.createThrow("product.was.not.possible.determine.type");
    }

    public static Class<?> getClass(SaleableUnitEntity entity) {
        if (PRODUCT.equals(entity.getType())) {
            return Product.class;
        } else if (SERVICE.equals(entity.getType())) {
            return Service.class;
        } else if (SaleableTypeEntity.PACKAGE.equals(entity.getType())) {
            return SalePackage.class;
        }

        throw ValidationException.createThrow("product.entity.without.type");
    }



    public static Class<?> getClass(SaleableUnit model) {
        if (model instanceof Product) {
            return ProductEntity.class;
        } else if (model instanceof Service) {
            return ServiceEntity.class;
        } else if (model instanceof SalePackage) {
            return SalePackageEntity.class;
        }

        throw ValidationException.createThrow("product.model.without.type");
    }
}
