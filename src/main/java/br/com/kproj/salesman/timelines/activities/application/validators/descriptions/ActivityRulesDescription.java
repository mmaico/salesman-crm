package br.com.kproj.salesman.timelines.activities.application.validators.descriptions;


import br.com.kproj.salesman.infrastructure.validators.IgnoreRules;
import br.com.kproj.salesman.infrastructure.validators.RuleKey;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;

import java.util.Set;

public class ActivityRulesDescription implements IgnoreRules {

    private Set<RuleKey> ignoreRules = Sets.newHashSet();

    public ActivityRulesDescription(RuleKey... rules) {
        ignoreRules.addAll(Lists.newArrayList(rules));
    }

    public static ActivityRulesDescription ignoreRules(RuleKey... rule) {
        return new ActivityRulesDescription(rule);
    }

    public static RuleKey ruleInvalidDelivery() {
        return RuleKey.key("task.invalid.timeline", "timeline");
    }
    public static RuleKey ruleInvalidDeadline() {
        return RuleKey.key("task.deadline.great.than.or.equals.today", "deadline");
    }
    public static RuleKey ruleTaskWithoutTitle() {
        return RuleKey.key("task.without.title", "title");
    }
    public static RuleKey ruleTaskWithoutStatus() {
        return RuleKey.key("task.without.status", "status");
    }
    public static RuleKey ruleTaskNotFoundOnUpdate() {
        return RuleKey.key("task.not.found.on.update", StringUtils.EMPTY);
    }

    @Override
    public Set<RuleKey> getToIgnore() {
        return ignoreRules;
    }
}
