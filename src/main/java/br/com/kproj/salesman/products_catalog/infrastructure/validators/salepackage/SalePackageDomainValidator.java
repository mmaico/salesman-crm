package br.com.kproj.salesman.products_catalog.infrastructure.validators.salepackage;

import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.validators.CheckRule;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.SaleableUnit;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.salepackage.SalePackageRepository;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.salepackage.SalePackageValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static br.com.kproj.salesman.infrastructure.helpers.RuleExpressionHelper.description;

@Component
public class SalePackageDomainValidator implements SalePackageValidator {

    @Autowired
    private SalePackageRepository repository;

    Map<String, CheckRule<SaleableUnit>> persistRules = new HashMap<>();

    {
        persistRules.put(description("salespackage.not.exists"), (salespackage) -> salespackage == null || salespackage.isNew() ||
                !repository.findOne(salespackage.getId()).isPresent()
        );

    }

    @Override
    public void checkRules(SaleableUnit saleableUnit) {

        Set<String> violations = persistRules.entrySet()
                .stream()
                .filter(e -> e.getValue().check(saleableUnit))
                .map(Map.Entry::getKey).collect(Collectors.toSet());

        hasErrors(violations).throwing(ValidationException.class);

    }
}
