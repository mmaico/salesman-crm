package br.com.kproj.salesman.infrastructure.model;


import java.util.HashMap;
import java.util.Map;

public class DslContext {

    private Map<String, Object> context = new HashMap<>();

    public Object get(Class<?> clazz) {

        return context.get(clazz.getName());
    }

    public void add(Class<?> clazz, Object object) {
        context.put(clazz.getName(), object);
    }
}
