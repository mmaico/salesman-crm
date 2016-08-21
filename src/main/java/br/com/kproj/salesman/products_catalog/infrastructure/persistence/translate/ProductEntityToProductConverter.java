package br.com.kproj.salesman.products_catalog.infrastructure.persistence.translate;

import br.com.kproj.salesman.infrastructure.entity.saleable.ProductEntity;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ProductEntityToProductConverter implements Converter<ProductEntity, Product> {

    @Autowired
    private MeasurementUnitEntityToUnitConverter unitConverter;

    @Autowired
    private SaleableUnitEntityConverter saleableUnitConverter;

    @Override
    public Product convert(ProductEntity productEntity, Object... args) {
        Product product = new Product();

        saleableUnitConverter.convert(productEntity, product);

        product.setUnit(unitConverter.convert(productEntity.getMeasurementUnit()));

        return product;
    }
}
