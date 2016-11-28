package br.com.kproj.salesman.accounts.contacts.application.validators;


import com.google.common.collect.Lists;

import java.util.List;

public class ContactIgnoreRules {

    private List<String> ignoreRules = Lists.newArrayList();

    public ContactIgnoreRules(String... rules) {
        ignoreRules.addAll(Lists.newArrayList(rules));
    }

    public static ContactIgnoreRules add(String... rule) {
        return new ContactIgnoreRules(rule);
    }

    public static String ruleInvalidCustomer() {
        return "customer.contact.invalid.customer";
    }
    public static String ruleInvalidName() {
        return "customer.contact.invalid.name";
    }
    public static String ruleInvalidEmail() {
        return "customer.contact.invalid.email";
    }

    public List<String> getIgnoreRules() {
        return ignoreRules;
    }

    public Boolean contains(String rule) {
        return this.ignoreRules.contains(rule);
    }
}
