package br.com.kproj.salesman.register.infrastructure.helpers;

import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ProductHelper {

    private static final Map<SaleableType, String> names = new HashMap<>();

    static {
        names.put(SaleableType.PRODUCT, "Produto");
        names.put(SaleableType.SERVICE, "Servi&ccedil;o");
    }

    public String type(SaleableType type) {
        return names.get(type);
    }
}
