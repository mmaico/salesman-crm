package br.com.kproj.salesman.infrastructure.validators;


import com.google.common.collect.Sets;

import java.util.Set;

public class DefaultIgnoreRules implements IgnoreRules {

    @Override
    public Set<RuleKey> getToIgnore() {
        return Sets.newHashSet();
    }

    public static DefaultIgnoreRules getIgnoreRules() {
        return new DefaultIgnoreRules();
    }
}
