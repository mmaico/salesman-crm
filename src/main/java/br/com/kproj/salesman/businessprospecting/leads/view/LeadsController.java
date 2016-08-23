package br.com.kproj.salesman.businessprospecting.leads.view;


import br.com.kproj.salesman.infrastructure.entity.leads.Lead;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.helpers.view.NormalizeEntityRequest;
import br.com.kproj.salesman.infrastructure.security.helpers.SecurityHelper;
import br.com.kproj.salesman.negotiation.infrastructure.validators.LeadValidator;
import br.com.kproj.salesman.businessprospecting.leads.application.LeadsApplication;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@RestController
public class LeadsController {

    @Autowired
    private LeadsApplication service;

    @Autowired
    private NormalizeEntityRequest normalizeEntityRequest;

    @Autowired
    private LeadValidator validator;

    @Autowired
    private SecurityHelper security;


    @InitBinder(value = "lead")
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }


    @RequestMapping(value = "/leads/save", method = RequestMethod.POST)
    public  @ResponseBody String save(@ModelAttribute @Validated Lead lead, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors());
        }

        normalizeEntityRequest.doNestedReference(lead);
        Lead leadLoaded = service.save(lead);

        return "/leads/" + leadLoaded.getId();
    }

    @RequestMapping(value = "/leads/save", method = RequestMethod.PUT)
    public @ResponseBody String update(@ModelAttribute Lead lead, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors());
        }
        if (lead.isNew()) {
            throw new ValidationException(Sets.newHashSet("leads.update.without.id"));
        }

        normalizeEntityRequest.addFieldsToUpdate(lead);
        Lead leadLoaded = service.save(lead);

        return "/leads/" + leadLoaded.getId();
    }
}
