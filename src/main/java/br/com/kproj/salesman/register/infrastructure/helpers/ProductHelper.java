package br.com.kproj.salesman.register.infrastructure.helpers;

import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableTypeEntity;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.infrastructure.repository.Pager;
import br.com.kproj.salesman.register.application.contract.saleable.SaleableApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class ProductHelper {

    private static final Map<SaleableTypeEntity, String> names = new HashMap<>();

    @Autowired
    private SaleableApplication service;

    static {
        names.put(SaleableTypeEntity.PRODUCT, "Produto");
        names.put(SaleableTypeEntity.SERVICE, "Servi&ccedil;o");
        names.put(SaleableTypeEntity.PACKAGE, "Pacote");
    }

    public String type(SaleableTypeEntity type) {
        return names.get(type);
    }

    public Iterable<SaleableUnitEntity> getAllProducts() {
        return service.findAll(Pager.build().withPageNumer(1).withPageSize(10000));
    }

    public Iterable<SaleableUnitEntity> getProducts() {
        return service.getByType(SaleableTypeEntity.PRODUCT);
    }

    public Iterable<SaleableUnitEntity> getServices() {
        return service.getByType(SaleableTypeEntity.SERVICE);
    }

    public Iterable<SaleableUnitEntity> getPackages() {
        return service.getByType(SaleableTypeEntity.PACKAGE);
    }

    public SaleableUnitEntity load(Long id) {
        Optional<SaleableUnitEntity> result = service.getOne(id);

        return result.isPresent() ? result.get() : null;
    }

    public Boolean isPackage(SaleableUnitEntity saleableUnit) {
        if (saleableUnit == null || saleableUnit.getType() == null) {
            return Boolean.FALSE;
        }

        return SaleableTypeEntity.PACKAGE.equals(saleableUnit.getType());
    }
}
