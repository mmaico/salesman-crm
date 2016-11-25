package br.com.kproj.salesman.accounts.addresses.view.support.update;

import br.com.kproj.salesman.accounts.addresses.domain.model.address.Address;
import br.com.kproj.salesman.accounts.customers.domain.model.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static br.com.kproj.salesman.infrastructure.helpers.RequestBody.body;
import static br.com.kproj.salesman.infrastructure.model.ModelIdentifiable.When.when;

@Component
public class AddressUpdateFields {

    private HttpServletRequest request;

    @Autowired
    public AddressUpdateFields(HttpServletRequest request) {
        this.request = request;
    }

    public void addFieldsToUpdate(Address address)  {

        address.addField("street", when(body(request).has("street")));
        address.addField("city", when(body(request).has("city")));
        address.addField("state", when(body(request).has("state")));
        address.addField("zipCode", when(body(request).has("zipCode")));
        address.addField("country", when(body(request).has("country")));
        address.addField("type", when(body(request).has("type")));
    }
}
