package br.com.kproj.salesman.medias.storage.view.support.update;

import br.com.kproj.salesman.medias.storage.domain.model.definition.StorageDefinition;
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

    public void addFieldsToUpdate(StorageDefinition storageDefinition)  {

        storageDefinition.addField("name", when(body(request).has("name")));
        storageDefinition.addField("email", when(body(request).has("email")));
        storageDefinition.addField("phone", when(body(request).has("phone")));
        storageDefinition.addField("position", when(body(request).has("position")));
    }
}
