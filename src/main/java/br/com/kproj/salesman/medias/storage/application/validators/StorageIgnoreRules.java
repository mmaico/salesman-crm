package br.com.kproj.salesman.medias.storage.application.validators;


import br.com.kproj.salesman.infrastructure.validators.RuleKey;
import com.google.common.collect.Lists;

import java.util.List;

public class StorageIgnoreRules {

    private List<String> ignoreRules = Lists.newArrayList();

    public StorageIgnoreRules(String... rules) {
        ignoreRules.addAll(Lists.newArrayList(rules));
    }

    public static StorageIgnoreRules add(String... rule) {
        return new StorageIgnoreRules(rule);
    }

    public static RuleKey ruleInvalidName() {
        return RuleKey.key("storage.invalid.name", "name");
    }

    public List<String> getIgnoreRules() {
        return ignoreRules;
    }

    public Boolean contains(String rule) {
        return this.ignoreRules.contains(rule);
    }
}
