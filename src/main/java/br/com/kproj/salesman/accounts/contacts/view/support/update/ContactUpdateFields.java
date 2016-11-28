package br.com.kproj.salesman.accounts.contacts.view.support.update;

import br.com.kproj.salesman.accounts.contacts.domain.model.contact.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static br.com.kproj.salesman.infrastructure.helpers.RequestBody.body;
import static br.com.kproj.salesman.infrastructure.model.ModelIdentifiable.When.when;

@Component
public class ContactUpdateFields {

    private HttpServletRequest request;

    @Autowired
    public ContactUpdateFields(HttpServletRequest request) {
        this.request = request;
    }

    public void addFieldsToUpdate(Contact contact)  {

        contact.addField("name", when(body(request).has("name")));
        contact.addField("email", when(body(request).has("email")));
        contact.addField("phone", when(body(request).has("phone")));
        contact.addField("position", when(body(request).has("position")));
    }
}
