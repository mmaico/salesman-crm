package br.com.kproj.salesman.timelines.activities.application.validators.descriptions;


import br.com.kproj.salesman.infrastructure.validators.IgnoreRules;
import br.com.kproj.salesman.infrastructure.validators.RuleKey;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.Set;

public class ActivityRulesDescription implements IgnoreRules {

    private Set<RuleKey> ignoreRules = Sets.newHashSet();

    public ActivityRulesDescription(RuleKey... rules) {
        ignoreRules.addAll(Lists.newArrayList(rules));
    }

    public static ActivityRulesDescription ignoreRules(RuleKey... rule) {
        return new ActivityRulesDescription(rule);
    }

    public static RuleKey ruleValidTimeline() {
        return RuleKey.key("activity.invalid.timeline", "timeline");
    }
    public static RuleKey ruleValidDescription() {
        return RuleKey.key("activity.empty.description", "description");
    }
    public static RuleKey ruleValidUser() {
        return RuleKey.key("activity.invalid.user", "user");
    }

    public static RuleKey ruleTag() {
        return RuleKey.key("activity.invalid.tag", "tag");
    }

    @Override
    public Set<RuleKey> getToIgnore() {
        return ignoreRules;
    }
}
