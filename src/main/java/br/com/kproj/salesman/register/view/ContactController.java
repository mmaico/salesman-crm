package br.com.kproj.salesman.register.view;

import br.com.kproj.salesman.infrastructure.entity.Contact;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.helpers.NormalizeEntityRequest;
import br.com.kproj.salesman.infrastructure.repository.Pager;
import br.com.kproj.salesman.register.application.ContactService;
import br.com.kproj.salesman.register.infrastructure.validators.ContactValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

import static br.com.kproj.salesman.infrastructure.entity.builders.ClientBuilder.createClient;
import static br.com.kproj.salesman.infrastructure.entity.builders.ProviderBuilder.createProvider;

@RestController
public class ContactController {

    @Autowired
    private ContactService service;

    @Autowired
    private ContactValidator validator;

    @Autowired
    private NormalizeEntityRequest normalizeEntityRequest;

    @InitBinder(value = "contact")
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @RequestMapping(value = "/clients/{clientId}/contacts/save", method = RequestMethod.POST)
    public  @ResponseBody ResponseEntity save(@ModelAttribute @Validated Contact contact, @PathVariable("clientId") Long clientId,
                                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors());
        }

        normalizeEntityRequest.doNestedReference(contact);
        service.register(createClient(clientId).build(), contact);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/clients/{clientId}/contacts/save", method = RequestMethod.PUT)
    public @ResponseBody ResponseEntity update(@ModelAttribute @Validated Contact contact, @PathVariable("clientId") Long clientId,
                                               BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors());
        }

        normalizeEntityRequest.addFieldsToUpdate(contact);
        service.register(createClient(clientId).build(), contact);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/providers/{providerId}/contacts/save", method = RequestMethod.POST)
    public  @ResponseBody ResponseEntity saveProvider(@ModelAttribute @Validated Contact contact, @PathVariable("providerId") Long providerId,
                                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors());
        }

        normalizeEntityRequest.addFieldsToUpdate(contact);
        service.register(createProvider(providerId).build(), contact);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/providers/{providerId}/contacts/save", method = RequestMethod.PUT)
    public @ResponseBody ResponseEntity updateProvider(@ModelAttribute @Validated Contact contact, @PathVariable("providerId") Long providerId,
                                               BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors());
        }

        normalizeEntityRequest.addFieldsToUpdate(contact);
        service.register(createProvider(providerId).build(), contact);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value="/contacts/list")
    public ModelAndView list(@PageableDefault(page=0, size=15)Pageable pageable, Model model) {
        Pager pager = Pager.binding(pageable);

        Iterable<Contact> result = this.service.findAll(pager);

        model.addAttribute("contacts", result);
        return new ModelAndView("/contacts/list");
    }

    @RequestMapping(value="/contacts/create")
    public ModelAndView newClient(Model model) {

        return new ModelAndView("/contacts/edit");
    }

    @RequestMapping(value="/contacts/{contactId}")
    public ModelAndView viewInfo(@RequestParam(defaultValue="edit",required=false, value="template") String templateName,
                                 @PathVariable Long contactId, Model model) {

        Optional<Contact> result = this.service.getOne(contactId);

        model.addAttribute("contact", result.isPresent() ? result.get(): null);
        return new ModelAndView("/contacts/" + templateName);
    }

}
