package br.com.kproj.salesman.negotiation.negotiation.view.support.update;

import br.com.kproj.salesman.negotiation.negotiation.domain.model.negotiation.Negotiation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static br.com.kproj.salesman.infrastructure.helpers.RequestBody.body;
import static br.com.kproj.salesman.infrastructure.model.ModelIdentifiable.When.when;

@Component
public class NegotiationUpdateFields {

    private HttpServletRequest request;

    @Autowired
    public NegotiationUpdateFields(HttpServletRequest request) {
        this.request = request;
    }

    public void addFieldsToUpdate(Negotiation negotiation)  {

        negotiation.addField("deliveryForeCast", when(body(request).has("deliveryForeCast")));
        negotiation.addField("introduction", when(body(request).has("introduction")));
        negotiation.addField("careOf", when(body(request).has("careOf")));
        negotiation.addField("discount", when(body(request).has("discount")));
        negotiation.addField("ammountPayable", when(body(request).has("ammountPayable")));
        negotiation.addField("temperature", when(body(request).has("temperature")));

        negotiation.addField("customer", when(body(request).has("customer")));
        negotiation.addField("region", when(body(request).has("region")));
        negotiation.addField("seller", when(body(request).has("seller")));
    }
}
