package br.com.kproj.salesman.accounts.customers.application.validators;


import com.google.common.collect.Lists;

import java.util.List;

public class CustomerIgnoreRules {

    private List<String> ignoreRules = Lists.newArrayList();

    public CustomerIgnoreRules(String... rules) {
        ignoreRules.addAll(Lists.newArrayList(rules));
    }

    public static CustomerIgnoreRules add(String... rule) {
        return new CustomerIgnoreRules(rule);
    }

    public static String ruleInvalidId() {
        return "customer.invalid.id";
    }
    public static String ruleInvalidName() {
        return "customer.invalid.name";
    }

    public List<String> getIgnoreRules() {
        return ignoreRules;
    }

    public Boolean contains(String rule) {
        return this.ignoreRules.contains(rule);
    }
}
