package br.com.kproj.salesman.medias.storage.application.validators;


import com.google.common.collect.Lists;

import java.util.List;

public class StorageDefinitionIgnoreRules {

    private List<String> ignoreRules = Lists.newArrayList();

    public StorageDefinitionIgnoreRules(String... rules) {
        ignoreRules.addAll(Lists.newArrayList(rules));
    }

    public static StorageDefinitionIgnoreRules add(String... rule) {
        return new StorageDefinitionIgnoreRules(rule);
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
