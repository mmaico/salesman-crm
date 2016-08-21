package br.com.kproj.salesman.products_catalog.infrastructure.validators;

import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.validators.CheckRule;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.SaleableUnit;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.SaleableValidator;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static br.com.kproj.salesman.infrastructure.helpers.RuleExpressionHelper.description;

@Component
public class SaleableDomainValidator implements SaleableValidator {

    Map<String, CheckRule<SaleableUnit>> persistRules = new HashMap<>();

    {
        persistRules.put(description("product.with.invalid.price"), (saleable) ->
                BigDecimal.ZERO.compareTo(saleable.getPrice()) > 0
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
