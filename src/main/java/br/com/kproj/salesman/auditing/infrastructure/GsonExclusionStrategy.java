package br.com.kproj.salesman.auditing.infrastructure;


import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class GsonExclusionStrategy implements ExclusionStrategy {


    @Override
    public boolean shouldSkipField(FieldAttributes f) {
        return f.getAnnotation(ExcludeAuditingField.class) != null;
    }

    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
        return Boolean.FALSE;
    }

    public static GsonExclusionStrategy create() {
        return new GsonExclusionStrategy();
    }
}
