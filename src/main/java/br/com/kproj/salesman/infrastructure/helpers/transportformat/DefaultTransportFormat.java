package br.com.kproj.salesman.infrastructure.helpers.transportformat;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultTransportFormat implements TransportFormat {

    @Autowired
    private Gson gson;

    @Override
    public String format(Object object) {
        return gson.toJson(object);
    }
}
