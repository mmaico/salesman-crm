package br.com.kproj.salesman.products_catalog.view;

import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.infrastructure.entity.saleable.ServiceEntity;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.helpers.view.NormalizeEntityRequest;
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

    @RequestMapping(value = "/services/add", method = RequestMethod.POST)
    public @ResponseBody
    SaleableUnitEntity save(@ModelAttribute @Validated ServiceEntity service, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors());
        }

        SaleableUnitEntity saleable = this.service.register(service);

        return saleable;
    }

    @RequestMapping(value = "/services/add", method = RequestMethod.PUT)
    public @ResponseBody void update(@ModelAttribute ServiceEntity service, BindingResult bindingResult) {
        normalizeEntityRequest.addFieldsToUpdate(service);
        this.service.register(service);
    }

    @RequestMapping("/services/list")
    public ModelAndView list(@PageableDefault(page=0, size=150000)Pageable pageable, Model model) {

        Pager pager = Pager.binding(pageable);

        Iterable<ServiceEntity> result = this.service.findAll(pager);

        model.addAttribute("services", result);
        return new ModelAndView("/saleables/services/serviceList");
    }
    
    @RequestMapping(value="/services/{serviceId}")
    public ModelAndView viewInfo(@PathVariable Long serviceId, Model model) {
        
        Optional<ServiceEntity> result = this.service.getOne(serviceId);

        model.addAttribute("service", result.isPresent() ? result.get(): null);
        return new ModelAndView("/saleables/services/serviceDetail");
    }

    @RequestMapping(value="/services/create")
    public ModelAndView newProduct() {

        return new ModelAndView("/saleables/services/serviceEdit");
    }

}
