package br.com.kproj.salesman.infrastructure.configuration;


import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class GsonExclusionStrategy implements ExclusionStrategy {


    @Override
    public boolean shouldSkipField(FieldAttributes f) {
        return f.getAnnotation(ExcludeField.class) != null;
    }

    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
        return Boolean.FALSE;
    }

    public static GsonExclusionStrategy create() {
        return new GsonExclusionStrategy();
    }
}
