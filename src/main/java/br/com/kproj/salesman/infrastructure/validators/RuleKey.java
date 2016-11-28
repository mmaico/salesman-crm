package br.com.kproj.salesman.infrastructure.validators;


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
}
