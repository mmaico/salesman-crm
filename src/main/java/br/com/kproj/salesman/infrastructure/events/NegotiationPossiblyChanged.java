package br.com.kproj.salesman.infrastructure.events;


import br.com.kproj.salesman.infrastructure.helpers.AutowireHelper;
import br.com.kproj.salesman.infrastructure.service.SerializerObject;
import org.springframework.beans.factory.annotation.Autowired;

public class NegotiationPossiblyChanged {

    private final String message;
    @Autowired
    private SerializerObject serializer;


    public NegotiationPossiblyChanged(String message) {
        this.message = message;
    }

    public NegotiationPossiblyChanged(Object message) {
        AutowireHelper.autowire(this);
        this.message = serializer.serialize(message);
    }

    public String getMessage() {
        return message;
    }

    public static NegotiationPossiblyChanged possiblyChanged(Object object) {
        return new NegotiationPossiblyChanged(object);
    }
}
