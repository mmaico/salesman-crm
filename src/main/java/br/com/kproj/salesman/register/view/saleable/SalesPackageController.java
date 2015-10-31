package br.com.kproj.salesman.register.view.saleable;

import br.com.kproj.salesman.infrastructure.entity.saleable.SalePackage;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnit;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.helpers.NormalizeEntityRequest;
import br.com.kproj.salesman.infrastructure.repository.Pager;
import br.com.kproj.salesman.register.application.contract.saleable.SalePackageService;
import br.com.kproj.salesman.register.infrastructure.validators.SalePackageValidator;
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
public class SalesPackageController {

    @Autowired
    private SalePackageService service;

    @Autowired
    private SalePackageValidator validator;

    @Autowired
    private NormalizeEntityRequest normalizeEntityRequest;

    @InitBinder(value = {"salePackage"})
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);

        
    }

    @RequestMapping(value = "/sales-package/save", method = RequestMethod.POST)
    public @ResponseBody
    SalePackage save(@ModelAttribute @Validated SalePackage salePackage, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors());
        }

        SalePackage salePackageResult = service.register(salePackage);

        return salePackageResult;
    }

    @RequestMapping(value = "/sales-package/save", method = RequestMethod.PUT)
    public @ResponseBody
    SaleableUnit update(@ModelAttribute @Validated SalePackage salePackage, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors());
        }
        normalizeEntityRequest.addFieldsToUpdate(salePackage);
        SaleableUnit saleable = service.register(salePackage);

        return saleable;
    }

    @RequestMapping("/sales-package/list")
    public ModelAndView list(@PageableDefault(page=0, size=150000)Pageable pageable, Model model) {

        Pager pager = Pager.binding(pageable);

        Iterable<SalePackage> result = this.service.findAll(pager);

        model.addAttribute("products", result);
        return new ModelAndView("/products/list-items");
    }
    
    @RequestMapping(value="/sales-package/{packageId}")
    public ModelAndView viewInfo(@PathVariable Long packageId, Model model) {
        
        Optional<SalePackage> result = this.service.getOne(packageId);

        model.addAttribute("product", result.isPresent() ? result.get(): null);
        return new ModelAndView("/products/edit");
    }

    @RequestMapping(value = "/packages/{packageId}/json", method = RequestMethod.GET)
    public @ResponseBody
    SaleableUnit getProduct(@PathVariable Long packageId) {

        Optional<SalePackage> saleable = service.getOne(packageId);

        return saleable.isPresent() ? saleable.get() : null;
    }

    @RequestMapping(value="/sales-package/create")
    public ModelAndView newProduct() {

        return new ModelAndView("/products/edit");
    }

}
