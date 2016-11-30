package br.com.kproj.salesman.negotiation.saleable_negotiated.application.validators;


import br.com.kproj.salesman.infrastructure.validators.IgnoreRules;
import br.com.kproj.salesman.infrastructure.validators.RuleKey;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;

import java.util.Set;

public class NegotiatedRulesDescription implements IgnoreRules {

    private Set<RuleKey> ignoreRules = Sets.newHashSet();

    public NegotiatedRulesDescription(RuleKey... rules) {
        ignoreRules.addAll(Lists.newArrayList(rules));
    }

    public static NegotiatedRulesDescription ignoreRules(RuleKey... rule) {
        return new NegotiatedRulesDescription(rule);
    }

    public static RuleKey rulePrice() {
        return RuleKey.key("negotiated.invalid.price", "price");
    }
    public static RuleKey ruleQuantity() {
        return RuleKey.key("negotiated.invalid.quantity", "quantity");
    }
    public static RuleKey ruleOriginalPrice() {
        return RuleKey.key("negotiated.invalid.originalPrice", "originalPrice");
    }

    public static RuleKey ruleSaleable() {
        return RuleKey.key("negotiated.invalid.saleable", StringUtils.EMPTY);
    }

    public static RuleKey rulePackage() {
        return RuleKey.key("negotiated.invalid.package", StringUtils.EMPTY);
    }

    @Override
    public Set<RuleKey> getToIgnore() {
        return ignoreRules;
    }
}
