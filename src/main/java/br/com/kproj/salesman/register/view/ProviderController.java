package br.com.kproj.salesman.register.view;

import java.util.Optional;

import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.helpers.NormalizeEntityRequest;
import br.com.kproj.salesman.infrastructure.repository.Pager;
import br.com.kproj.salesman.register.application.ProviderService;
import br.com.kproj.salesman.register.infraestructure.validators.ClientVOValidator;
import br.com.kproj.salesman.register.view.dto.ProviderDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ProviderController {

    @Autowired
    private ProviderService service;

    @Autowired
    private ClientVOValidator validator;

    @Autowired
    private NormalizeEntityRequest normalizeEntityRequest;

    @InitBinder(value = "providerDTO")
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @RequestMapping(value = "/providers/save", method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute @Validated ProviderDTO providerDTO, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getFieldErrors());
        }
        normalizeEntityRequest.doNestedReference(providerDTO.getProvider());
        Person clientSaved = service.register(providerDTO.getProvider());

        model.addAttribute("client", clientSaved);
        return new ModelAndView("client");
    }

    @RequestMapping(value = "/providers/save", method = RequestMethod.PUT)
    public ModelAndView update(@ModelAttribute @Validated ProviderDTO providerDTO, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getFieldErrors());
        }
        normalizeEntityRequest.addFieldsToUpdate(providerDTO.getProvider());
        Person clientSaved = service.register(providerDTO.getProvider());

        model.addAttribute("provider", clientSaved);
        return new ModelAndView("provider");
    }

    @RequestMapping(value="/providers/list")
    public ModelAndView list(@PageableDefault(page=0, size=15)Pageable pageable, Model model) {
        Pager pager = Pager.binding(pageable);

        Iterable<Person> result = this.service.findAll(pager);

        model.addAttribute("clients", result);
        return new ModelAndView("client");
    }
    
    @RequestMapping(value="/providers/{providerId}")
    public ModelAndView viewInfo(Long providerId, Model model) {
        
        Optional<Person> result = this.service.getOne(providerId);

        model.addAttribute("provider", result.get());
        return new ModelAndView("provider");
    }
}
