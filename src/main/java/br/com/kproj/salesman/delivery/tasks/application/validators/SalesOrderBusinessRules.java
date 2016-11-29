package br.com.kproj.salesman.delivery.tasks.application.validators;

import br.com.kproj.salesman.delivery.tasks.domain.model.sales.SalesOrder;
import br.com.kproj.salesman.delivery.tasks.domain.model.sales.SalesValidator;
import br.com.kproj.salesman.infrastructure.validators.CheckRule;
import br.com.kproj.salesman.infrastructure.validators.RuleKey;
import br.com.kproj.salesman.infrastructure.validators.RulesExecute;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static br.com.kproj.salesman.delivery.tasks.application.validators.descriptions.SalesOrderDescription.*;
import static br.com.kproj.salesman.infrastructure.helpers.CollectionsHelper.isEmptySafe;

@Component
public class SalesOrderBusinessRules implements SalesValidator {



    private Map<RuleKey, CheckRule<SalesOrder>> rules = new HashMap<>();
    {
        rules.put(ruleSalesOrder(), sales -> sales.isNew());
        rules.put(ruleRegion(), sales -> sales.getRegion() == null || sales.getRegion().isNew());

        rules.put(ruleProducts(), sales ->
                isEmptySafe(sales.getItems()) || sales.getItems().stream()
                        .filter(item -> item.getProduct() == null || item.getProduct().isNew())
                        .count() > 0);

    }

    @Override
    public void checkRules(SalesOrder salesOrder) {
        RulesExecute.runRules(rules, salesOrder);
    }
}
