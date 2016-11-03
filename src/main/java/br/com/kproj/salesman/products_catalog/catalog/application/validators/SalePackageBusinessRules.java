package br.com.kproj.salesman.products_catalog.catalog.application.validators;

import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.validators.CheckRule;
import br.com.kproj.salesman.products_catalog.catalog.domain.model.saleables.SaleableUnit;
import br.com.kproj.salesman.products_catalog.catalog.domain.model.saleables.SaleableUnitRepository;
import br.com.kproj.salesman.products_catalog.catalog.domain.model.salepackage.SalePackageRepository;
import br.com.kproj.salesman.products_catalog.catalog.domain.model.salepackage.SalePackageValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static br.com.kproj.salesman.products_catalog.catalog.application.validators.IgnoreRules.rulePackageNotExists;
import static br.com.kproj.salesman.products_catalog.catalog.application.validators.IgnoreRules.ruleSaleableNotExists;
import static com.google.common.collect.Lists.newArrayList;

@Component
public class SalePackageBusinessRules implements SalePackageValidator {

    @Autowired
    private SalePackageRepository repository;

    @Autowired
    private SaleableUnitRepository saleableRepository;

    Map<String, CheckRule<SaleableUnit>> persistRules = new HashMap<>();

    {
        persistRules.put(rulePackageNotExists(), (salespackage) -> salespackage == null
                || salespackage.isNew()
                || !repository.findOne(salespackage.getId()).isPresent()
        );

        persistRules.put(ruleSaleableNotExists(), (salespackage) ->
                !saleableRepository.findOne(salespackage.getId()).isPresent()
        );
    }

    @Override
    public void checkRules(SaleableUnit saleableUnit) {
        checkRules(saleableUnit, IgnoreRules.add(StringUtils.EMPTY));
    }

    @Override
    public void checkRules(SaleableUnit saleableUnit, IgnoreRules rules) {

        Set<String> violations = persistRules.entrySet()
                .stream()
                .filter(item -> {
                    if (newArrayList(rules.getIgnoreRules()).contains(item.getKey())) {
                        return Boolean.FALSE;
                    }
                    try {
                        return item.getValue().check(saleableUnit);
                    } catch (Exception ex) {
                        return Boolean.TRUE;
                    }
                }).map(Map.Entry::getKey).collect(Collectors.toSet());

        hasErrors(violations).throwing(ValidationException.class);
    }



}
