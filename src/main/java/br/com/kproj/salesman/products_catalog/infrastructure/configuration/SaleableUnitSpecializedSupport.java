package br.com.kproj.salesman.products_catalog.infrastructure.configuration;

//@Configuration
public class SaleableUnitSpecializedSupport {


//    @Bean(name="saleablesConverters")
//    public Map<SaleableTypeEntity, SelectConverter> converters(SalePackageEntityToSalePackageConverter packageConverter,
//                                                               ProductEntityToProductConverter productConverter,
//                                                               ServiceEntityToServiceConverter serviceConverter) {
//        final Map<SaleableTypeEntity, SelectConverter> converters = new HashMap<>();
//        converters.put(PACKAGE, entity -> packageConverter.convert((SalePackageEntity) entity));
//        converters.put(PRODUCT, entity -> productConverter.convert((ProductEntity) entity));
//        converters.put(SERVICE, entity -> serviceConverter.convert((ServiceEntity) entity));
//
//        return converters;
//    }
//
//    @Bean(name="saleablesRepositories")
//    public Map<SaleableTypeEntity, PersistRepository<? extends SaleableUnitEntity>> repositories(ProductRepositorySpringData productRepository,
//                                                                                                      ServiceRepositorySpringData serviceRepository,
//                                                                                                      SalePackageRepositorySpringData salepackageRepository) {
//        Map<SaleableTypeEntity, PersistRepository<? extends SaleableUnitEntity>> repositories = new HashMap<>();
//        repositories.put(PACKAGE, (entity -> salepackageRepository.save((SalePackageEntity) entity)));
//        repositories.put(PRODUCT, (entity -> productRepository.save((ProductEntity) entity)));
//        repositories.put(SERVICE, (entity -> serviceRepository.save((ServiceEntity) entity)));
//
//        return repositories;
//    }
//
//
//    public interface SelectConverter<T extends SaleableUnit> {
//
//        T select(SaleableUnitEntity entity);
//    }
//
//    public interface PersistRepository<T extends SaleableUnitEntity> {
//
//        T save(SaleableUnitEntity entity);
//    }
}
