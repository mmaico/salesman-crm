package br.com.kproj.salesman.register.view;

import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.helpers.NormalizeEntityRequest;
import br.com.kproj.salesman.infrastructure.repository.Pager;
import br.com.kproj.salesman.register.application.ProviderService;
import br.com.kproj.salesman.register.infrastructure.validators.ProviderVOValidator;
import br.com.kproj.salesman.register.view.dto.ProviderDTO;
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

@RestController
public class ProviderController {

    @Autowired
    private ProviderService service;

    @Autowired
    private ProviderVOValidator validator;

    @Autowired
    private NormalizeEntityRequest normalizeEntityRequest;

    @InitBinder(value = "providerDTO")
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @RequestMapping(value = "/providers/save", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity save(@ModelAttribute @Validated ProviderDTO providerDTO, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors());
        }
        normalizeEntityRequest.doNestedReference(providerDTO.getProvider());
        Person clientSaved = service.register(providerDTO.getProvider());

        model.addAttribute("provider", clientSaved);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/providers/save", method = RequestMethod.PUT)
    public @ResponseBody ResponseEntity update(@ModelAttribute @Validated ProviderDTO providerDTO, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors());
        }
        normalizeEntityRequest.addFieldsToUpdate(providerDTO.getProvider());
        Person clientSaved = service.register(providerDTO.getProvider());

        model.addAttribute("provider", clientSaved);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value="/providers/list")
    public ModelAndView list(@PageableDefault(page=0, size=15)Pageable pageable, Model model) {
        Pager pager = Pager.binding(pageable);

        Iterable<Person> result = this.service.findAll(pager);

        model.addAttribute("providers", result);
        return new ModelAndView("/providers/list");
    }

    @RequestMapping(value="/providers/create")
    public ModelAndView newProvider() {

        return new ModelAndView("/providers/newProvider");
    }
    
    @RequestMapping(value="/providers/{providerId}")
    public ModelAndView viewInfo(Long providerId, Model model) {
        
        Optional<Person> result = this.service.getOne(providerId);

        model.addAttribute("provider", result.isPresent() ? result.get(): null);
        return new ModelAndView("/providers/edit");
    }
}
