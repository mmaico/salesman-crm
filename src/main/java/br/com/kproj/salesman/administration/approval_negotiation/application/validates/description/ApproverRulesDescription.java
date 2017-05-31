package br.com.kproj.salesman.administration.approval_negotiation.application.validates.description;


import br.com.kproj.salesman.infrastructure.validators.IgnoreRules;
import br.com.kproj.salesman.infrastructure.validators.RuleKey;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.Set;

public class ApproverRulesDescription implements IgnoreRules {

    private Set<RuleKey> ignoreRules = Sets.newHashSet();

    public ApproverRulesDescription(RuleKey... rules) {
        ignoreRules.addAll(Lists.newArrayList(rules));
    }

    public static ApproverRulesDescription ignoreRules(RuleKey... rule) {
        return new ApproverRulesDescription(rule);
    }

    public static RuleKey ruleNotExists() {
        return RuleKey.key("approver.not.exists");
    }
    public static RuleKey ruleUserNotExists() {
        return RuleKey.key("approver.user.not.exists");
    }


    @Override
    public Set<RuleKey> getToIgnore() {
        return ignoreRules;
    }
}
