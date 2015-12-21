package br.com.kproj.salesman.register.view;

import br.com.kproj.salesman.infrastructure.entity.Contact;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.helpers.NormalizeEntityRequest;
import br.com.kproj.salesman.register.application.contract.ContactApplication;
import br.com.kproj.salesman.register.infrastructure.validators.ContactValidator;
import br.com.kproj.salesman.timeline.application.TimelineApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClientContactController {

    @Autowired
    private ContactApplication service;

    @Autowired
    private ContactValidator validator;

    @Autowired
    private TimelineApplication timelineApplication;

    @Autowired
    private NormalizeEntityRequest normalizeEntityRequest;

    @InitBinder(value = "contact")
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @RequestMapping(value = "/clients/{clientId}/contacts/save", method = RequestMethod.POST)
    public  @ResponseBody String save(@ModelAttribute @Validated Contact contact,
                                      @PathVariable Long clientId, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors());
        }

        normalizeEntityRequest.doNestedReference(contact);
        Contact contactLoaded = service.register(contact);

        return "/contacts/" + contactLoaded.getId();
    }

}
