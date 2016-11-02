package br.com.kproj.salesman.products_catalog.application.validators;

import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.validators.CheckRule;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.SaleableUnit;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.SaleableValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static br.com.kproj.salesman.infrastructure.helpers.RuleExpressionHelper.description;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Component
public class SaleableDomainValidator implements SaleableValidator {

    Map<String, CheckRule<SaleableUnit>> persistRules = new HashMap<>();

    {
        persistRules.put("saleable.null", (saleable) -> saleable == null);
        persistRules.put("saleable.without.name", (saleable) -> isBlank(saleable.getName()));

    }

    @Override
    public void checkRules(SaleableUnit saleableUnit) {

        Set<String> violations = persistRules.entrySet()
                .stream()
                .filter(item -> {
                    try {
                        return item.getValue().check(saleableUnit);
                    } catch (Exception e) {
                        return Boolean.TRUE;
                    }
                }).map(Map.Entry::getKey).collect(Collectors.toSet());

        hasErrors(violations).throwing(ValidationException.class);

    }
}
