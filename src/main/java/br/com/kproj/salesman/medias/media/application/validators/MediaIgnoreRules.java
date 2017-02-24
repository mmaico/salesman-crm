package br.com.kproj.salesman.medias.media.application.validators;


import br.com.kproj.salesman.infrastructure.validators.RuleKey;
import com.google.common.collect.Lists;

import java.util.List;

public class MediaIgnoreRules {

    private List<String> ignoreRules = Lists.newArrayList();

    public MediaIgnoreRules(String... rules) {
        ignoreRules.addAll(Lists.newArrayList(rules));
    }

    public static MediaIgnoreRules add(String... rule) {
        return new MediaIgnoreRules(rule);
    }

    public static RuleKey ruleFile() {
        return RuleKey.key("media.invalid.file", "file");
    }

    public static RuleKey ruleStorageName() {
        return RuleKey.key("storage.not.found");
    }

    public List<String> getIgnoreRules() {
        return ignoreRules;
    }

    public Boolean contains(String rule) {
        return this.ignoreRules.contains(rule);
    }
}
