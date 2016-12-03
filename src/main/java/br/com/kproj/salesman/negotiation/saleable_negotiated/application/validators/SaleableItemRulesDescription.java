package br.com.kproj.salesman.negotiation.saleable_negotiated.application.validators;


import br.com.kproj.salesman.infrastructure.validators.IgnoreRules;
import br.com.kproj.salesman.infrastructure.validators.RuleKey;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;

import java.util.Set;

public class SaleableItemRulesDescription implements IgnoreRules {

    private Set<RuleKey> ignoreRules = Sets.newHashSet();

    public SaleableItemRulesDescription(RuleKey... rules) {
        ignoreRules.addAll(Lists.newArrayList(rules));
    }

    public static SaleableItemRulesDescription ignoreRules(RuleKey... rule) {
        return new SaleableItemRulesDescription(rule);
    }

    public static RuleKey ruleNegotiated() {
        return RuleKey.key("negotiated.saleable.item.with.invalid.negotiated", "negotiated");
    }

    public static RuleKey ruleSaleableItemExists() {
        return RuleKey.key("negotiated.saleable.item.with.already.exists", StringUtils.EMPTY);
    }

    public static RuleKey ruleSaleable() {
        return RuleKey.key("negotiated.saleable.item.invalid.saleable", StringUtils.EMPTY);
    }

    public static RuleKey rulePackage() {
        return RuleKey.key("negotiated.saleable.item.invalid.package", StringUtils.EMPTY);
    }

    public static RuleKey ruleSaleableDoesNotBelongPackage() {
        return RuleKey.key("negotiated.saleable.item.saleable.doesnot.belong.package", StringUtils.EMPTY);
    }

    @Override
    public Set<RuleKey> getToIgnore() {
        return ignoreRules;
    }
}
