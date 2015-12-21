package br.com.kproj.salesman.register.view;

import br.com.kproj.salesman.infrastructure.entity.Contact;
import br.com.kproj.salesman.infrastructure.entity.timeline.Timeline;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.helpers.NormalizeEntityRequest;
import br.com.kproj.salesman.infrastructure.repository.Pager;
import br.com.kproj.salesman.register.application.contract.ContactApplication;
import br.com.kproj.salesman.register.infrastructure.validators.ContactValidator;
import br.com.kproj.salesman.timeline.application.TimelineApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

import static br.com.kproj.salesman.infrastructure.entity.builders.ContactBuilder.createContact;

@RestController
public class ContactController {

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

    @RequestMapping(value = "/contacts/save", method = RequestMethod.POST)
    public  @ResponseBody String save(@ModelAttribute @Validated Contact contact,
                                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors());
        }

        normalizeEntityRequest.doNestedReference(contact);
        Contact contactLoaded = service.register(contact);

        return "/contacts/" + contactLoaded.getId();
    }

    @RequestMapping(value = "/contacts/save", method = RequestMethod.PUT)
    public @ResponseBody String update(@ModelAttribute @Validated Contact contact,
                                               BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors());
        }

        normalizeEntityRequest.addFieldsToUpdate(contact);
        Contact contactLoaded = service.register(contact);

        return "/contacts/" + contactLoaded.getId();
    }


    @RequestMapping(value="/contacts/list")
    public ModelAndView list(@PageableDefault(page=0, size=150000)Pageable pageable, Model model) {
        Pager pager = Pager.binding(pageable);

        Iterable<Contact> result = this.service.findAll(pager);

        model.addAttribute("contacts", result);
        return new ModelAndView("/contacts/list-items");
    }

    @RequestMapping(value="/contacts/create")
    public ModelAndView newClient(Model model) {

        return new ModelAndView("/contacts/edit");
    }

    @RequestMapping(value="/contacts/{contactId}")
    public ModelAndView viewInfo(@RequestParam(defaultValue="edit",required=false, value="template") String templateName,
                                 @PathVariable Long contactId, Model model) {

        Optional<Contact> result = this.service.getOne(contactId);
        Timeline timeline = timelineApplication.register(createContact(contactId).build());

        model.addAttribute("timeline", timeline);
        model.addAttribute("contact", result.isPresent() ? result.get(): null);
        return new ModelAndView("/contacts/" + templateName);
    }

}
