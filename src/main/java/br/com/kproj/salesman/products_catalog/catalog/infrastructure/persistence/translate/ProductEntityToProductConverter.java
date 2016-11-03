package br.com.kproj.salesman.products_catalog.catalog.infrastructure.persistence.translate;

import br.com.kproj.salesman.infrastructure.entity.saleable.ProductEntity;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.products_catalog.catalog.domain.model.products.Product;
import com.trex.clone.BusinessModelClone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ProductEntityToProductConverter implements Converter<ProductEntity, Product> {

    private MeasurementUnitEntityToUnitConverter unitConverter;


    @Autowired
    public ProductEntityToProductConverter(MeasurementUnitEntityToUnitConverter unitConverter) {
        this.unitConverter = unitConverter;
    }

    @Override
    public Product convert(ProductEntity productEntity, Object... args) {
        Product product = new Product();

        BusinessModelClone.from(productEntity.getSaleable()).merge(product);
        product.setUnit(unitConverter.convert(productEntity.getMeasurementUnit()));

        return product;
    }
}
