package br.com.kproj.salesman.infrastructure.configuration.encapsulating;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JsonSerializerImpl<T> implements JsonSerializer <T> {

    @Autowired
    private Gson gson;


    @Override
    public String serialize(T object) {
        return gson.toJson(object);
    }

    @Override
    public T deserialize(String value, Class<T> clazz) {
        return gson.fromJson(value, clazz);
    }
}
