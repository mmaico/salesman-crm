package br.com.kproj.salesman.delivery.tasks.application.validators.descriptions;


import br.com.kproj.salesman.infrastructure.validators.IgnoreRules;
import br.com.kproj.salesman.infrastructure.validators.RuleKey;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;

import java.util.Set;

public class SubscriberRulesDescription implements IgnoreRules {

    private Set<RuleKey> ignoreRules = Sets.newHashSet();

    public SubscriberRulesDescription(RuleKey... rules) {
        ignoreRules.addAll(Lists.newArrayList(rules));
    }

    public static SubscriberRulesDescription ignoreRules(RuleKey... rule) {
        return new SubscriberRulesDescription(rule);
    }

    public static RuleKey ruleUser() {
        return RuleKey.key("subscribe.task.invalid.user", "user");
    }
    public static RuleKey ruleTask() {
        return RuleKey.key("subscribe.task.invalid.task", "task");
    }
    public static RuleKey ruleSubscriber() {
        return RuleKey.key("subscribe.task.unsubscribe.invalid", StringUtils.EMPTY);
    }

    @Override
    public Set<RuleKey> getToIgnore() {
        return ignoreRules;
    }
}
