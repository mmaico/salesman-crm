package br.com.kproj.salesman.register.view.providers;

import br.com.kproj.salesman.infrastructure.entity.ContactEntity;
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

import static br.com.kproj.salesman.infrastructure.entity.builders.ProviderBuilder.createProvider;

@RestController
public class ProviderContactController {

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

    @RequestMapping(value = "/providers/{providerId}/contacts/add", method = {RequestMethod.POST, RequestMethod.PUT})
    public  @ResponseBody void clientContact(@ModelAttribute @Validated ContactEntity contact,
                                      @PathVariable Long providerId, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors());
        }

        normalizeEntityRequest.doNestedReference(contact);
        normalizeEntityRequest.addFieldsToUpdate(contact);
        service.register(contact, createProvider(providerId).build());
    }


    @RequestMapping(value = "/providers/{providerId}/contacts", method = RequestMethod.GET)
    public ModelAndView getContacts(@PathVariable Long providerId, Model model) {

        List<ContactEntity> result = service.getContactsByProvider(createProvider(providerId).build());

        model.addAttribute("contactItems", result);
        model.addAttribute("client", createProvider(providerId).build());
        return new ModelAndView("providers/edit-contact");
    }

}
