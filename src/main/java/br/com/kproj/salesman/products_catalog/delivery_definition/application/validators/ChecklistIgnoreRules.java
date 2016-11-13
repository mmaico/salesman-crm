package br.com.kproj.salesman.products_catalog.delivery_definition.application.validators;


import com.google.common.collect.Lists;

import java.util.List;

public class ChecklistIgnoreRules {

    private List<String> ignoreRules = Lists.newArrayList();

    public ChecklistIgnoreRules(String... rules) {
        ignoreRules.addAll(Lists.newArrayList(rules));
    }

    public static ChecklistIgnoreRules add(String... rule) {
        return new ChecklistIgnoreRules(rule);
    }

    public static String ruleNameNotPresent() {
        return "checklist.definition.invalid.name";
    }

    public static String ruleTaskNotExists() {
        return "checklist.definition.invalid.task";
    }

    public static String ruleChecklistNotExists() {
        return "checklist.definition.not.exists";
    }

    public List<String> getIgnoreRules() {
        return ignoreRules;
    }
}
