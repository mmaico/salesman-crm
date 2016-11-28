package br.com.kproj.salesman.negotiation.negotiation.application.validators;


import com.google.common.collect.Lists;

import java.util.List;

public class NegotiationIgnoreRules {

    private List<String> ignoreRules = Lists.newArrayList();

    public NegotiationIgnoreRules(String... rules) {
        ignoreRules.addAll(Lists.newArrayList(rules));
    }

    public static NegotiationIgnoreRules add(String... rule) {
        return new NegotiationIgnoreRules(rule);
    }

    public static String ruleCustomer() {
        return "negotiation.invalid.customer";
    }
    public static String ruleSeller() {
        return "negotiation.invalid.seller";
    }
    public static String ruleForecast() {
        return "negotiation.invalid.date.forecast";
    }
    public static String ruleAmmountPayable() {
        return "negotiation.invalid.ammount.payable";
    }
    public static String ruleApprovalProcess() {
        return "negotiation.in.approval.process";
    }


    public List<String> getIgnoreRules() {
        return ignoreRules;
    }

    public Boolean contains(String rule) {
        return this.ignoreRules.contains(rule);
    }
}
