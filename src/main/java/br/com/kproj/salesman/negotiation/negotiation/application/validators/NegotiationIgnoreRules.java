package br.com.kproj.salesman.negotiation.negotiation.application.validators;


import br.com.kproj.salesman.infrastructure.validators.RuleKey;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class NegotiationIgnoreRules {

    private List<String> ignoreRules = Lists.newArrayList();

    public NegotiationIgnoreRules(String... rules) {
        ignoreRules.addAll(Lists.newArrayList(rules));
    }

    public static NegotiationIgnoreRules add(String... rule) {
        return new NegotiationIgnoreRules(rule);
    }

    public static RuleKey ruleCustomer() {
        return RuleKey.key("negotiation.invalid.customer", "customer");
    }
    public static RuleKey ruleSeller() {
        return RuleKey.key("negotiation.invalid.seller", "seller");
    }
    public static RuleKey ruleForecast() {
        return RuleKey.key("negotiation.invalid.date.forecast", "deliveryForeCast");
    }
    public static RuleKey ruleAmmountPayable() {
        return RuleKey.key("negotiation.invalid.ammount.payable", "ammountPayable");
    }

    public static RuleKey ruleApprovalProcess() {
        return RuleKey.key("negotiation.in.approval.process", StringUtils.EMPTY);
    }


    public List<String> getIgnoreRules() {
        return ignoreRules;
    }

    public Boolean contains(String rule) {
        return this.ignoreRules.contains(rule);
    }
}
