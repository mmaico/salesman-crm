package br.com.kproj.salesman.infrastructure.configuration.encapsulating;


public interface JsonSerializer<T> {

    String serialize(T object);

    T deserialize(String value, Class<T> clazz);
}
