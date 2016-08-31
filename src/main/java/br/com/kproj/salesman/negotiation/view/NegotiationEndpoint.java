package br.com.kproj.salesman.negotiation.view;

import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import br.com.kproj.salesman.infrastructure.security.authentication.LoggedUser;
import br.com.kproj.salesman.infrastructure.security.helpers.SecurityHelper;
import br.com.kproj.salesman.negotiation.application.NegotiationFacade;
import br.com.kproj.salesman.negotiation.domain.model.negotiation.Negotiation;
import br.com.kproj.salesman.negotiation.domain.model.negotiation.NegotiationChangeTemperature;
import br.com.kproj.salesman.negotiation.domain.model.seller.Seller;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import com.fasterxml.jackson.databind.module.SimpleDeserializers;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.BeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.BeanSerializer;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.databind.ser.std.BeanSerializerBase;
import com.fasterxml.jackson.databind.type.SimpleType;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

import static br.com.kproj.salesman.negotiation.domain.model.negotiation.NegotiationChangeTemperature.createChangeTemperature;
import static br.com.kproj.salesman.negotiation.domain.model.seller.SellerBuilder.createSeller;

@RestController
public class NegotiationEndpoint {

    @Autowired
    private NegotiationFacade service;

    @Autowired
    private SecurityHelper security;



    @RequestMapping(value = "/negotiations", method = RequestMethod.POST)
    public @ResponseBody Negotiation save(@RequestBody Negotiation negotiation) {

        Optional<Negotiation> negotiationSaved = service.register(negotiation);

        return negotiationSaved.get();
    }

    @RequestMapping(value = "/negotiations", method = RequestMethod.PUT)
    public @ResponseBody void update(@RequestBody Negotiation negotiation) {

        service.register(negotiation);
    }

    @RequestMapping(value = "/negotiations/{negotiationId}", method = RequestMethod.GET)
    public @ResponseBody Negotiation get(@PathVariable Long negotiationId) {

        Optional<Negotiation> result = service.getOne(negotiationId);

        return result.get();
    }

    @RequestMapping(value="/negotiations", method = RequestMethod.PATCH)
    public @ResponseBody void changeTemperature(@RequestBody Negotiation negotiation) {
        LoggedUser loggedUser = security.getPrincipal().getLoggedUser();

        Seller seller = createSeller(loggedUser.getId()).build();
        NegotiationChangeTemperature negotiationChangeTemperature = createChangeTemperature(negotiation, negotiation.getTemperature());

        service.changeTemperature(seller, negotiationChangeTemperature);
    }
    
}
