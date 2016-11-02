package br.com.kproj.salesman.products_catalog.application.validators;


import com.google.common.collect.Lists;

import java.util.List;

public class IgnoreRules {

    private List<String> ignoreRules = Lists.newArrayList();

    public IgnoreRules(String... rules) {
        ignoreRules.addAll(Lists.newArrayList(rules));
    }

    public static IgnoreRules add(String... rule) {
        return new IgnoreRules(rule);
    }

    public static String rulePackageNotExists() {
        return "salespackage.not.exists";
    }

    public static String ruleSaleableNotExists() {
        return "saleable.not.exists.to.creation.package";
    }

    public List<String> getIgnoreRules() {
        return ignoreRules;
    }
}
