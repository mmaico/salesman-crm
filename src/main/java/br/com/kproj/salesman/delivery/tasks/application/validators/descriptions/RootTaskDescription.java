package br.com.kproj.salesman.delivery.tasks.application.validators.descriptions;


import br.com.kproj.salesman.infrastructure.validators.IgnoreRules;
import br.com.kproj.salesman.infrastructure.validators.RuleKey;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.Set;

public class RootTaskDescription implements IgnoreRules {

    private Set<RuleKey> ignoreRules = Sets.newHashSet();

    public RootTaskDescription(RuleKey... rules) {
        ignoreRules.addAll(Lists.newArrayList(rules));
    }

    public static RootTaskDescription ignoreRules(RuleKey... rule) {
        return new RootTaskDescription(rule);
    }

    public static RuleKey ruleTask() {
        return RuleKey.key("roottask.with.invalid.task", "task");
    }
    public static RuleKey ruleSpecialization() {
        return RuleKey.key("roottask.with.already.exists.specialization");
    }

    @Override
    public Set<RuleKey> getToIgnore() {
        return ignoreRules;
    }
}
