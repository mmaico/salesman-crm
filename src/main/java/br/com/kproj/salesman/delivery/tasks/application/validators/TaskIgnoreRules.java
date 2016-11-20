package br.com.kproj.salesman.delivery.tasks.application.validators;


import com.google.common.collect.Lists;

import java.util.List;

public class TaskIgnoreRules {

    private List<String> ignoreRules = Lists.newArrayList();

    public TaskIgnoreRules(String... rules) {
        ignoreRules.addAll(Lists.newArrayList(rules));
    }

    public static TaskIgnoreRules add(String... rule) {
        return new TaskIgnoreRules(rule);
    }

    public static String ruleInvalidDelivery() {
        return "task.invalid.delivery";
    }

    public static String ruleInvalidDeadline() {
        return "task.deadline.great.than.or.equals.today";
    }
    public static String ruleTaskWithoutTitle() {
        return "task.without.title";
    }
    public static String ruleTaskWithoutStatus() {
        return "task.without.status";
    }
    public static String ruleTaskNotFoundOnUpdate() {
        return "task.not.found.on.update";
    }

    public List<String> getIgnoreRules() {
        return ignoreRules;
    }

    public Boolean contains(String rule) {
        return this.ignoreRules.contains(rule);
    }
}
