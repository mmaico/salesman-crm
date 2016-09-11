package br.com.kproj.salesman.delivery2.tasks.application.validators;

import br.com.kproj.salesman.delivery2.tasks.domain.model.sales.SalesOrder;
import br.com.kproj.salesman.delivery2.tasks.domain.model.sales.SalesOrderRepository;
import br.com.kproj.salesman.delivery2.tasks.domain.model.sales.SalesValidator;
import br.com.kproj.salesman.delivery2.tasks.domain.model.tasks.Task;
import br.com.kproj.salesman.delivery2.tasks.domain.model.tasks.TaskValidator;
import br.com.kproj.salesman.delivery2.tasks.domain.model.user.UserRepository;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.helpers.CollectionsHelper;
import br.com.kproj.salesman.infrastructure.validators.CheckRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.helpers.CollectionsHelper.isEmptySafe;
import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static br.com.kproj.salesman.infrastructure.helpers.RuleExpressionHelper.description;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Component
public class SalesOrderBusinessRules implements SalesValidator {



    Map<String, CheckRule<SalesOrder>> rules = new HashMap<>();
    {
        rules.put(description("generate.tasks.salesorder.without.id"), sales -> sales.isNew());
        rules.put(description("generate.tasks.salesorder.with.invalid.region"), sales ->
                sales.getRegion() == null || sales.getRegion().isNew());

        rules.put(description("generate.tasks.invalis.products.salesorder"), sales ->
                isEmptySafe(sales.getItems()) || sales.getItems().stream()
                        .filter(item -> item.getProduct() == null || item.getProduct().isNew())
                        .count() > 0);

    }

    @Override
    public void checkRules(SalesOrder salesOrder) {

        Set<String> violations = rules.entrySet()
                .stream()
                .filter(e -> e.getValue().check(salesOrder))
                .map(Map.Entry::getKey).collect(Collectors.toSet());

        hasErrors(violations).throwing(ValidationException.class);
    }
}
