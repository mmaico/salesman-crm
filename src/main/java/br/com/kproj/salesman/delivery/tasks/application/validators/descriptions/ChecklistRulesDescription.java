package br.com.kproj.salesman.delivery.tasks.application.validators.descriptions;


import br.com.kproj.salesman.infrastructure.validators.IgnoreRules;
import br.com.kproj.salesman.infrastructure.validators.RuleKey;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;

import java.util.Set;

public class ChecklistRulesDescription implements IgnoreRules {

    private Set<RuleKey> ignoreRules = Sets.newHashSet();

    public ChecklistRulesDescription(RuleKey... rules) {
        ignoreRules.addAll(Lists.newArrayList(rules));
    }

    public static ChecklistRulesDescription ignoreRules(RuleKey... rule) {
        return new ChecklistRulesDescription(rule);
    }

    public static RuleKey ruleInvalidTask() {
        return RuleKey.key("checklist.with.invalid.task", "task");
    }

    public static RuleKey ruleInvalidStatus() {
        return RuleKey.key("checklist.with.invalid.status", "done");
    }

    public static RuleKey ruleInvalidName() {
        return RuleKey.key("checklist.with.invalid.name", "name");
    }

    public static RuleKey ruleInvalidId() {
        return RuleKey.key("checklist.with.invalid.id", StringUtils.EMPTY);
    }


    @Override
    public Set<RuleKey> getToIgnore() {
        return ignoreRules;
    }
}
