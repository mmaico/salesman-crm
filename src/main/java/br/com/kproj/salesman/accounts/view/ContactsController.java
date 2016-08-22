package br.com.kproj.salesman.accounts.view;

import br.com.kproj.salesman.accounts.application.ContactFacade;
import br.com.kproj.salesman.accounts.domain.model.contact.Contact;
import br.com.kproj.salesman.accounts.domain.model.contact.ContactValidator;
import br.com.kproj.salesman.infrastructure.helpers.view.NormalizeAttrUpdateHelper;
import br.com.kproj.salesman.infrastructure.repository.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;


@RestController
public class ContactsController {

    @Autowired
    private ContactFacade service;

    @Autowired
    @Qualifier("contactViewValidator")
    private ContactValidator validator;

    @Autowired
    private NormalizeAttrUpdateHelper attributesToUpdate;


    @RequestMapping(value = "/accounts/{accountId}/contacts", method = RequestMethod.POST)
    public @ResponseBody Contact save(@ModelAttribute Contact contact) {

        validator.checkRules(contact);

        Optional<Contact> contactSaved = service.register(contact);

        return contactSaved.isPresent() ? contactSaved.get() : null;
    }

    @RequestMapping(value = "/accounts/contacts", method = RequestMethod.PUT)
    public @ResponseBody void update(@ModelAttribute Contact contact) {

        attributesToUpdate.addAttributesToUpdate(contact);
        service.register(contact);
    }

    @RequestMapping("/accounts/contacts")
    public ModelAndView list(@PageableDefault(size=150000)Pageable pageable, Model model) {

        Pager pager = Pager.binding(pageable);

        Iterable<Contact> result = this.service.findAll(pager);

        model.addAttribute("contacts", result);
        return new ModelAndView("/saleables/products/productList");
    }
    
    @RequestMapping(value="/accounts/contacts/{contactId}")
    public ModelAndView viewInfo(@PathVariable Long contactId, Model model) {
        
        Optional<Contact> result = this.service.getOne(contactId);

        model.addAttribute("contact", result.isPresent() ? result.get(): null);
        return new ModelAndView("/saleables/products/productDetail");
    }


    @RequestMapping(value="/accounts/contacts/create")
    public ModelAndView newContact() {

        return new ModelAndView("/saleables/products/productEdit");
    }

}
