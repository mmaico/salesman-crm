package br.com.kproj.salesman.administration.users.application.validators;


import br.com.kproj.salesman.infrastructure.validators.RuleKey;
import com.google.common.collect.Lists;

import java.util.List;

public class UserRulesDescription {

    private List<String> ignoreRules = Lists.newArrayList();

    public UserRulesDescription(String... rules) {
        ignoreRules.addAll(Lists.newArrayList(rules));
    }

    public static UserRulesDescription add(String... rule) {
        return new UserRulesDescription(rule);
    }

    public static RuleKey ruleLoginAlreadyExists() {
        return RuleKey.key("user.already.existis.with.login", "login");
    }

    public static RuleKey ruleInvalidPassword() {
        return RuleKey.key("user.invalid.password", "password");
    }

    public static RuleKey ruleEmail() {
        return RuleKey.key("user.invalid.email", "email");
    }

    public List<String> getIgnoreRules() {
        return ignoreRules;
    }

    public Boolean contains(String rule) {
        return this.ignoreRules.contains(rule);
    }
}
