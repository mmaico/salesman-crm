package br.com.kproj.salesman.products_catalog.application.validators;

import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.validators.CheckRule;
import br.com.kproj.salesman.products_catalog.domain.model.products.Product;
import br.com.kproj.salesman.products_catalog.domain.model.products.ProductValidator;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.SaleableUnitRepository;
import br.com.kproj.salesman.products_catalog.domain.model.unit.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;

@Component("productRulesCatalogModule")
public class ProductBusinessRules implements ProductValidator {

    private UnitRepository unitRepository;
    private SaleableUnitRepository repository;

    @Autowired
    public ProductBusinessRules(UnitRepository unitRepository, SaleableUnitRepository repository) {
        this.unitRepository = unitRepository;
        this.repository = repository;
    }

    Map<String, CheckRule<Product>> rules = new HashMap<>();
    {
        rules.put("product.with.invalid.unit", product ->
                product.getUnit() == null
                || product.getUnit().isNew()
                || !unitRepository.findOne(product.getUnit().getId()).isPresent());

        rules.put("product.without.id", product -> product.isNew());
        rules.put("not.exist.a.saleable.for.product", product -> !repository.findOne(product.getId()).isPresent());
    }

    @Override
    public void checkRules(Product product) {

        Set<String> violations = rules.entrySet()
                .stream()
                .filter(item -> {
                    try {
                        return item.getValue().check(product);
                    } catch(Exception e) {
                        return Boolean.TRUE;
                    }
                }).map(Map.Entry::getKey).collect(Collectors.toSet());

        hasErrors(violations).throwing(ValidationException.class);
    }
}
