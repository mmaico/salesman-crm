package br.com.kproj.salesman.products_catalog.catalog.view.support;


import java.util.Optional;
import java.util.stream.Stream;

import static br.com.kproj.salesman.infrastructure.exceptions.ValidationException.createThrow;

public enum SaleableType {
    PRODUCT("product"), SERVICE("service"), SALE_PACKAGE("salepackage");

    private String type;

    SaleableType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static SaleableType get(String name) {

        Optional<SaleableType> result = Stream.of(values())
                .filter(item -> item.type.equalsIgnoreCase(name))
                .findFirst();

        return result.orElseThrow(() -> createThrow("saleable.type.is.invalid"));
    }
}
