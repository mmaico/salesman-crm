package br.com.kproj.salesman.accounts.addresses.application.validators;


import com.google.common.collect.Lists;

import java.util.List;

public class AddressIgnoreRules {

    private List<String> ignoreRules = Lists.newArrayList();

    public AddressIgnoreRules(String... rules) {
        ignoreRules.addAll(Lists.newArrayList(rules));
    }

    public static AddressIgnoreRules add(String... rule) {
        return new AddressIgnoreRules(rule);
    }

    public static String ruleInvalidCustomer() {
        return "customer.address.invalid.customer";
    }
    public static String ruleInvalidType() {
        return "customer.address.invalid.type";
    }
    public static String ruleInvalidStreet() {
        return "customer.address.invalid.street";
    }

    public List<String> getIgnoreRules() {
        return ignoreRules;
    }

    public Boolean contains(String rule) {
        return this.ignoreRules.contains(rule);
    }
}
