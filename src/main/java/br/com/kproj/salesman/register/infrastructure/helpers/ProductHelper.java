package br.com.kproj.salesman.register.infrastructure.helpers;

import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableType;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnit;
import br.com.kproj.salesman.infrastructure.repository.Pager;
import br.com.kproj.salesman.register.application.contract.saleable.SaleableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ProductHelper {

    private static final Map<SaleableType, String> names = new HashMap<>();

    @Autowired
    private SaleableService service;

    static {
        names.put(SaleableType.PRODUCT, "Produto");
        names.put(SaleableType.SERVICE, "Servi&ccedil;o");
    }

    public String type(SaleableType type) {
        return names.get(type);
    }

    public Iterable<SaleableUnit> getAllProducts() {
        return service.findAll(Pager.build().withPageNumer(1).withPageSize(10000));
    }
}
