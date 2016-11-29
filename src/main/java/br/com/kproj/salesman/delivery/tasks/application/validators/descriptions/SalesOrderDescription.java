package br.com.kproj.salesman.delivery.tasks.application.validators.descriptions;


import br.com.kproj.salesman.infrastructure.validators.IgnoreRules;
import br.com.kproj.salesman.infrastructure.validators.RuleKey;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.Set;

public class SalesOrderDescription implements IgnoreRules {

    private Set<RuleKey> ignoreRules = Sets.newHashSet();

    public SalesOrderDescription(RuleKey... rules) {
        ignoreRules.addAll(Lists.newArrayList(rules));
    }

    public static SalesOrderDescription ignoreRules(RuleKey... rule) {
        return new SalesOrderDescription(rule);
    }

    public static RuleKey ruleSalesOrder() {
        return RuleKey.key("generate.delivery.salesorder.without.id");
    }
    public static RuleKey ruleRegion() {
        return RuleKey.key("generate.delivery.salesorder.with.invalid.region");
    }
    public static RuleKey ruleProducts() {
        return RuleKey.key("generate.delivery.invalis.products.salesorder");
    }

    @Override
    public Set<RuleKey> getToIgnore() {
        return ignoreRules;
    }
}
