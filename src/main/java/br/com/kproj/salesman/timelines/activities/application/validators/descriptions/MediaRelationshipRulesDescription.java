package br.com.kproj.salesman.timelines.activities.application.validators.descriptions;


import br.com.kproj.salesman.infrastructure.validators.IgnoreRules;
import br.com.kproj.salesman.infrastructure.validators.RuleKey;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.Set;

public class MediaRelationshipRulesDescription implements IgnoreRules {

    private Set<RuleKey> ignoreRules = Sets.newHashSet();

    public MediaRelationshipRulesDescription(RuleKey... rules) {
        ignoreRules.addAll(Lists.newArrayList(rules));
    }

    public static MediaRelationshipRulesDescription ignoreRules(RuleKey... rule) {
        return new MediaRelationshipRulesDescription(rule);
    }

    public static RuleKey ruleInvalidMedia() {
        return RuleKey.key("media.invalid.relationship.media", "media");
    }
    public static RuleKey ruleInvalidActvity() {
        return RuleKey.key("media.invalid.relationship.activity", "activity");
    }

    public static RuleKey ruleRelationshipAlreadyExists() {
        return RuleKey.key("media.relationship.already.exists");
    }

    @Override
    public Set<RuleKey> getToIgnore() {
        return ignoreRules;
    }
}
