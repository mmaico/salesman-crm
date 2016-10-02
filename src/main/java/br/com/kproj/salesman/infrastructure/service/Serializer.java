package br.com.kproj.salesman.infrastructure.service;


public interface Serializer {

    <T> T deserialize(String json, Class<T> clazz);

    String serialize(Object object);
}
