package br.com.kproj.salesman.register.view.saleable;

import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnit;
import br.com.kproj.salesman.infrastructure.entity.saleable.Service;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.helpers.NormalizeEntityRequest;
import br.com.kproj.salesman.infrastructure.repository.Pager;

import br.com.kproj.salesman.register.application.contract.saleable.ServiceSaleableApplication;
import br.com.kproj.salesman.register.infrastructure.validators.SaleableValidator;
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


@RestController
public class ServiceController {

    @Autowired
    private ServiceSaleableApplication service;

    @Autowired
    private SaleableValidator validator;

    @Autowired
    private NormalizeEntityRequest normalizeEntityRequest;

    @InitBinder(value = {"service"})
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);

        
    }

    @RequestMapping(value = "/services/save", method = RequestMethod.POST)
    public @ResponseBody
    SaleableUnit save(@ModelAttribute @Validated Service service, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors());
        }

        SaleableUnit saleable = this.service.register(service);

        return saleable;
    }

    @RequestMapping(value = "/services/save", method = RequestMethod.PUT)
    public @ResponseBody
    SaleableUnit update(@ModelAttribute @Validated Service service, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors());
        }
        normalizeEntityRequest.addFieldsToUpdate(service);
        SaleableUnit saleable = this.service.register(service);

        return saleable;
    }

    @RequestMapping("/services/list")
    public ModelAndView list(@PageableDefault(page=0, size=150000)Pageable pageable, Model model) {

        Pager pager = Pager.binding(pageable);

        Iterable<Service> result = this.service.findAll(pager);

        model.addAttribute("products", result);
        return new ModelAndView("/saleable/services/list-items");
    }
    
    @RequestMapping(value="/services/{serviceId}")
    public ModelAndView viewInfo(@PathVariable Long serviceId, Model model) {
        
        Optional<Service> result = this.service.getOne(serviceId);

        model.addAttribute("product", result.isPresent() ? result.get(): null);
        return new ModelAndView("/saleable/services/edit");
    }

    @RequestMapping(value = "/services/{serviceId}/json", method = RequestMethod.GET)
    public @ResponseBody
    SaleableUnit getProduct(@PathVariable Long serviceId) {

        Optional<Service> saleable = service.getOne(serviceId);

        return saleable.isPresent() ? saleable.get() : null;
    }

    @RequestMapping(value="/services/create")
    public ModelAndView newProduct() {

        return new ModelAndView("/saleable/services/edit");
    }

}
