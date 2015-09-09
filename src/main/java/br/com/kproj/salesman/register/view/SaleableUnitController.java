package br.com.kproj.salesman.register.view;

import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnit;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.helpers.NormalizeEntityRequest;
import br.com.kproj.salesman.infrastructure.repository.Pager;
import br.com.kproj.salesman.register.application.SaleableUnitService;
import br.com.kproj.salesman.register.infrastructure.validators.ProductValidator;
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
public class SaleableUnitController {

    @Autowired
    private SaleableUnitService service;

    @Autowired
    private ProductValidator validator;

    @Autowired
    private NormalizeEntityRequest normalizeEntityRequest;

    @InitBinder(value = {"product"})
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);

        
    }

    @RequestMapping(value = "/products/save", method = RequestMethod.POST)
    public @ResponseBody
    SaleableUnit save(@ModelAttribute @Validated SaleableUnit saleableUnit, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors());
        }

        SaleableUnit saleable = service.register(saleableUnit);

        return saleable;
    }

    @RequestMapping(value = "/products/save", method = RequestMethod.PUT)
    public @ResponseBody
    SaleableUnit update(@ModelAttribute @Validated SaleableUnit saleableUnit, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors());
        }
        normalizeEntityRequest.addFieldsToUpdate(saleableUnit);
        SaleableUnit saleable = service.register(saleableUnit);

        return saleable;
    }

    @RequestMapping("/products/list")
    public ModelAndView list(@PageableDefault(page=0, size=15)Pageable pageable, Model model) {

        Pager pager = Pager.binding(pageable);

        Iterable<SaleableUnit> result = this.service.findAll(pager);

        model.addAttribute("products", result);
        return new ModelAndView("/products/list");
    }
    
    @RequestMapping(value="/products/{productId}")
    public ModelAndView viewInfo(@PathVariable Long productId, Model model) {
        
        Optional<SaleableUnit> result = this.service.getOne(productId);

        model.addAttribute("product", result.isPresent() ? result.get(): null);
        return new ModelAndView("/products/edit");
    }

    @RequestMapping(value="/products/create")
    public ModelAndView newProduct() {

        return new ModelAndView("/products/edit");
    }

}
