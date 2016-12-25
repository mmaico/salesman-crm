package br.com.kproj.salesman.assistants.calendar.application.validators;


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

    public static RuleKey activityNotExists() {
        return RuleKey.key("calendar.activity.not.exist", StringUtils.EMPTY);
    }

    public static RuleKey ruleCalendar() {
        return RuleKey.key("calendar.activity.without.calendar", "calendar");
    }

    public static RuleKey ruleTitle() {
        return RuleKey.key("calendar.activity.not.have.title", "title");
    }

    public static RuleKey ruleStartDate() {
        return RuleKey.key("calendar.activity.without.startdate", StringUtils.EMPTY);
    }

    public static RuleKey ruleEndDate() {
        return RuleKey.key("calendar.activity.without.enddate", StringUtils.EMPTY);
    }

    public static RuleKey ruleRangeDate() {
        return RuleKey.key("calendar.activity.startdate.cannotbe.greaterthan.enddate", "start");
    }

    public static RuleKey ruleAllDay() {
        return RuleKey.key("period.if.allday.cannothave.hours", "allDay");
    }


    @Override
    public Set<RuleKey> getToIgnore() {
        return ignoreRules;
    }
}
