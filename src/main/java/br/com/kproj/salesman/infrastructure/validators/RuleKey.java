package br.com.kproj.salesman.infrastructure.validators;


import org.apache.commons.lang3.StringUtils;

public class RuleKey {

    private final String name;
    private final String field;


    public RuleKey(String name, String field) {
        this.name = name;
        this.field = field;
    }

    public String getName() {
        return name;
    }

    public String getField() {
        return field;
    }

    public static RuleKey key(String name, String field) {
        return new RuleKey(name, field);
    }

    public static RuleKey key(String name) {
        return new RuleKey(name, StringUtils.EMPTY);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RuleKey ruleKey = (RuleKey) o;

        return name != null ? name.equals(ruleKey.name) : ruleKey.name == null;

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
