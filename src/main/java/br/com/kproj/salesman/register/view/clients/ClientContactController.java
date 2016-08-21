package br.com.kproj.salesman.register.view.clients;

import br.com.kproj.salesman.infrastructure.entity.Contact;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.helpers.view.NormalizeEntityRequest;
import br.com.kproj.salesman.register.application.contract.ContactApplication;
import br.com.kproj.salesman.register.infrastructure.validators.ContactValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static br.com.kproj.salesman.infrastructure.entity.builders.ClientBuilder.createClient;

@RestController
public class ClientContactController {

    @Autowired
    private ContactApplication service;

    @Autowired
    private ContactValidator validator;

    @Autowired
    private NormalizeEntityRequest normalizeEntityRequest;

    @InitBinder(value = "contact")
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @RequestMapping(value = "/clients/{clientId}/contacts/save", method = {RequestMethod.POST, RequestMethod.PUT})
    public  @ResponseBody void clientContact(@ModelAttribute @Validated Contact contact,
                                      @PathVariable Long clientId, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors());
        }

        normalizeEntityRequest.doNestedReference(contact);
        normalizeEntityRequest.addFieldsToUpdate(contact);
        service.register(contact, createClient(clientId).build());
    }


    @RequestMapping(value = "/clients/{clientId}/contacts", method = RequestMethod.GET)
    public ModelAndView getContacts(@PathVariable Long clientId, Model model) {

        List<Contact> result = service.getContactsByClient(createClient(clientId).build());

        model.addAttribute("contactItems", result);
        model.addAttribute("client", createClient(clientId).build());
        return new ModelAndView("clients/edit-contact");
    }

}
