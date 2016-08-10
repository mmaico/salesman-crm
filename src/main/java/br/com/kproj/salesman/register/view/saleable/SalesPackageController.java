package br.com.kproj.salesman.register.view.saleable;

import br.com.kproj.salesman.infrastructure.entity.builders.SalePackageBuilder;
import br.com.kproj.salesman.infrastructure.entity.builders.SaleableUnitBuilder;
import br.com.kproj.salesman.infrastructure.entity.saleable.SalePackageEntity;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.helpers.NormalizeEntityRequest;
import br.com.kproj.salesman.infrastructure.repository.Pager;
import br.com.kproj.salesman.register.application.contract.saleable.SalePackageApplication;
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
    private SalePackageApplication service;

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
    SalePackageEntity save(@ModelAttribute @Validated SalePackageEntity salePackage, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors());
        }

        SalePackageEntity salePackageResult = service.register(salePackage);

        return salePackageResult;
    }

    @RequestMapping(value = "/sales-package/save", method = RequestMethod.PUT)
    public @ResponseBody
    void update(@ModelAttribute SalePackageEntity salePackage) {

        normalizeEntityRequest.addFieldsToUpdate(salePackage);
        service.register(salePackage);

    }

    @RequestMapping("/sales-package/list")
    public ModelAndView list(@PageableDefault(page=0, size=150000)Pageable pageable, Model model) {

        Pager pager = Pager.binding(pageable);

        Iterable<SalePackageEntity> result = this.service.findAll(pager);

        model.addAttribute("packages", result);
        return new ModelAndView("/saleables/packages/packageList");
    }
    
    @RequestMapping(value="/sales-package/{packageId}")
    public ModelAndView viewInfo(@PathVariable Long packageId, Model model) {
        
        Optional<SalePackageEntity> result = this.service.getOne(packageId);

        model.addAttribute("salesPackage", result.isPresent() ? result.get(): null);

        return new ModelAndView("/saleables/packages/packageDetail");
    }

    @RequestMapping(value="/sales-package/{packageId}/add-saleable/{saleableId}", method = RequestMethod.PUT)
    public @ResponseBody void addProductOrService(@PathVariable Long packageId, @PathVariable Long saleableId) {

        SalePackageEntity salePackage = SalePackageBuilder.createPackage(packageId).build();
        SaleableUnitEntity saleableUnit = SaleableUnitBuilder.createSaleableUnit(saleableId).build();

        this.service.addProductOrService(salePackage, saleableUnit);
    }

    @RequestMapping(value="/sales-package/{packageId}/remove-saleable/{saleableId}", method = RequestMethod.DELETE)
    public @ResponseBody void removeSaleable(@PathVariable Long packageId, @PathVariable Long saleableId) {

        SalePackageEntity salePackage = SalePackageBuilder.createPackage(packageId).build();
        SaleableUnitEntity saleableUnit = SaleableUnitBuilder.createSaleableUnit(saleableId).build();

        this.service.removeProductOrService(salePackage, saleableUnit);
    }

    @RequestMapping(value="/sales-package/create")
    public ModelAndView newProduct() {

        return new ModelAndView("/saleables/packages/packageEdit");
    }

}
