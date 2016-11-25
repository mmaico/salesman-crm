package br.com.kproj.salesman.accounts.customers.view.support.update;

import br.com.kproj.salesman.accounts.customers.domain.model.customer.Customer;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static br.com.kproj.salesman.infrastructure.helpers.RequestBody.body;
import static br.com.kproj.salesman.infrastructure.model.ModelIdentifiable.When.when;

@Component
public class CustomerUpdateFields {

    private HttpServletRequest request;

    @Autowired
    public CustomerUpdateFields(HttpServletRequest request) {
        this.request = request;
    }

    public void addFieldsToUpdate(Customer customer)  {

        customer.addField("name", when(body(request).has("name")));
        customer.addField("site", when(body(request).has("site")));
        customer.addField("description", when(body(request).has("description")));

    }
}
