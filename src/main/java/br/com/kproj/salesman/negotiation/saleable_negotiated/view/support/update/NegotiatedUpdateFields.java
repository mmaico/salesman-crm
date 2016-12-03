package br.com.kproj.salesman.negotiation.saleable_negotiated.view.support.update;

import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.negotiated.Negotiated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static br.com.kproj.salesman.infrastructure.helpers.RequestBody.body;
import static br.com.kproj.salesman.infrastructure.model.ModelIdentifiable.When.when;

@Component
public class NegotiatedUpdateFields {

    private HttpServletRequest request;

    @Autowired
    public NegotiatedUpdateFields(HttpServletRequest request) {
        this.request = request;
    }

    public void addFieldsToUpdate(Negotiated negotiated)  {
        negotiated.addField("price", when(body(request).has("price")));
        negotiated.addField("originalPrice", when(body(request).has("originalPrice")));
        negotiated.addField("quantity", when(body(request).has("quantity")));
    }
}
