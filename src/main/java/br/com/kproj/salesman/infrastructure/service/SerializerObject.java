package br.com.kproj.salesman.infrastructure.service;


public interface SerializerObject {

    <T> T deserialize(String json, Class<T> clazz);

    String serialize(Object object);
}
