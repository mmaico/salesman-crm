package br.com.kproj.salesman.negotiation.view;

import br.com.kproj.salesman.negotiation.domain.model.negotiation.Negotiation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.module.SimpleDeserializers;

import java.io.IOException;

/**
 * Created by mmaico on 8/29/16.
 */
public class TesteSerializer2 extends JsonDeserializer<Object> {


    @Override
    public Object deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        ObjectCodec codec = jp.getCodec();
        System.out.println("fdsfds");
        System.out.println("fdsfds");
        System.out.println("fdsfds");
        return null;
    }
}
