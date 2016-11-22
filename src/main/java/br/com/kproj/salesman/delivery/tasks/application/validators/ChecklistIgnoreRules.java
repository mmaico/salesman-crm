package br.com.kproj.salesman.delivery.tasks.application.validators;


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

    public static String ruleInvalidTask() {
        return "checklist.with.invalid.task";
    }

    public static String ruleInvalidStatus() {
        return "checklist.with.invalid.status";
    }

    public static String ruleInvalidName() {
        return "checklist.with.invalid.name";
    }

    public static String ruleInvalidId() {
        return "checklist.with.invalid.id";
    }

    public List<String> getIgnoreRules() {
        return ignoreRules;
    }

    public Boolean contains(String rule) {
        return this.ignoreRules.contains(rule);
    }
}
