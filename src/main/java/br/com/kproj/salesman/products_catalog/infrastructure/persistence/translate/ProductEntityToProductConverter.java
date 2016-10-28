package br.com.kproj.salesman.products_catalog.infrastructure.persistence.translate;

import br.com.kproj.salesman.infrastructure.entity.saleable.ProductEntity;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.products_catalog.domain.model.products.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ProductEntityToProductConverter implements Converter<ProductEntity, Product> {

    private MeasurementUnitEntityToUnitConverter unitConverter;

    private SaleableUnitEntityConverter saleableUnitConverter;

    @Autowired
    public ProductEntityToProductConverter(MeasurementUnitEntityToUnitConverter unitConverter,
                                           SaleableUnitEntityConverter saleableUnitConverter) {
        this.unitConverter = unitConverter;
        this.saleableUnitConverter = saleableUnitConverter;
    }

    @Override
    public Product convert(ProductEntity productEntity, Object... args) {
        Product product = new Product();
        //saleableUnitConverter.convert(productEntity, product);

        product.setUnit(unitConverter.convert(productEntity.getMeasurementUnit()));

        return product;
    }
}
