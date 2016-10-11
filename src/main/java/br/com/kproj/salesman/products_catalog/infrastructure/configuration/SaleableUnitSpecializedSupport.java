package br.com.kproj.salesman.products_catalog.infrastructure.configuration;

import br.com.kproj.salesman.infrastructure.entity.saleable.*;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.SaleableUnit;
import br.com.kproj.salesman.products_catalog.infrastructure.persistence.springdata.ProductRepositorySpringData;
import br.com.kproj.salesman.products_catalog.infrastructure.persistence.springdata.SalePackageRepositorySpringData;
import br.com.kproj.salesman.products_catalog.infrastructure.persistence.springdata.ServiceRepositorySpringData;
import br.com.kproj.salesman.products_catalog.infrastructure.persistence.translate.ProductEntityToProductConverter;
import br.com.kproj.salesman.products_catalog.infrastructure.persistence.translate.SalePackageEntityToSalePackageConverter;
import br.com.kproj.salesman.products_catalog.infrastructure.persistence.translate.ServiceEntityToServiceConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

import static br.com.kproj.salesman.infrastructure.entity.saleable.SaleableTypeEntity.*;

@Configuration
public class SaleableUnitSpecializedSupport {


    @Bean(name="saleablesConverters")
    public Map<SaleableTypeEntity, SelectConverter> converters(SalePackageEntityToSalePackageConverter packageConverter,
                                                               ProductEntityToProductConverter productConverter,
                                                               ServiceEntityToServiceConverter serviceConverter) {
        final Map<SaleableTypeEntity, SelectConverter> converters = new HashMap<>();
        converters.put(PACKAGE, entity -> packageConverter.convert((SalePackageEntity) entity));
        converters.put(PRODUCT, entity -> productConverter.convert((ProductEntity) entity));
        converters.put(SERVICE, entity -> serviceConverter.convert((ServiceEntity) entity));

        return converters;
    }

    @Bean(name="saleablesRepositories")
    public Map<SaleableTypeEntity, PersistRepository<? extends SaleableUnitEntity>> repositories(ProductRepositorySpringData productRepository,
                                                                                                      ServiceRepositorySpringData serviceRepository,
                                                                                                      SalePackageRepositorySpringData salepackageRepository) {
        Map<SaleableTypeEntity, PersistRepository<? extends SaleableUnitEntity>> repositories = new HashMap<>();
        repositories.put(PACKAGE, (entity -> salepackageRepository.save((SalePackageEntity) entity)));
        repositories.put(PRODUCT, (entity -> productRepository.save((ProductEntity) entity)));
        repositories.put(SERVICE, (entity -> serviceRepository.save((ServiceEntity) entity)));

        return repositories;
    }


    public interface SelectConverter<T extends SaleableUnit> {

        T select(SaleableUnitEntity entity);
    }

    public interface PersistRepository<T extends SaleableUnitEntity> {

        T save(SaleableUnitEntity entity);
    }
}
