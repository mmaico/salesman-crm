package br.com.kproj.salesman.products_catalog.catalog.view;


import br.com.kproj.salesman.infrastructure.helpers.view.NormalizeAttrUpdateHelper;
import br.com.kproj.salesman.infrastructure.repository.Pager;
import br.com.kproj.salesman.products_catalog.catalog.application.SalePackageFacade;
import br.com.kproj.salesman.products_catalog.catalog.domain.model.saleables.SaleableBuilder;
import br.com.kproj.salesman.products_catalog.catalog.domain.model.saleables.SaleableUnit;
import br.com.kproj.salesman.products_catalog.catalog.domain.model.salepackage.SalePackage;
import br.com.kproj.salesman.products_catalog.catalog.domain.model.salepackage.SalePackageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;


@RestController
public class SalesPackageController {

    @Autowired
    private SalePackageFacade service;

    @Autowired
    private NormalizeAttrUpdateHelper attributesToUpdate;



    @RequestMapping(value = "/sales-package/add", method = RequestMethod.POST)
    public @ResponseBody SalePackage save(@ModelAttribute SalePackage salePackage) {

        Optional<SalePackage> result = service.register(salePackage);

        return result.isPresent() ? result.get() : null;
    }

    @RequestMapping(value = "/sales-package/add", method = RequestMethod.PUT)
    public @ResponseBody
    void update(@ModelAttribute SalePackage salePackage) {

        attributesToUpdate.addAttributesToUpdate(salePackage);
        service.register(salePackage);

    }

    @RequestMapping("/sales-package/list")
    public ModelAndView list(@PageableDefault(size=150000)Pageable pageable, Model model) {

        Pager pager = Pager.binding(pageable);

        Iterable<SalePackage> result = this.service.findAll(pager);

        model.addAttribute("packages", result);
        return new ModelAndView("/saleables/packages/packageList");
    }
    
    @RequestMapping(value="/sales-package/{packageId}")
    public ModelAndView viewInfo(@PathVariable Long packageId, Model model) {
        
        Optional<SalePackage> result = this.service.getOne(packageId);

        model.addAttribute("salesPackage", result.isPresent() ? result.get(): null);

        return new ModelAndView("/saleables/packages/packageDetail");
    }

    @RequestMapping(value="/sales-package/{packageId}/add-saleable/{saleableId}", method = RequestMethod.PUT)
    public @ResponseBody void addProductOrService(@PathVariable Long packageId, @PathVariable Long saleableId) {

        SalePackage salePackage = SalePackageBuilder.createPackage(packageId).build();
        SaleableUnit saleableUnit = SaleableBuilder.createSaleable(saleableId).build();

        this.service.addSaleable(salePackage, saleableUnit);
    }

    @RequestMapping(value="/sales-package/{packageId}/remove-saleable/{saleableId}", method = RequestMethod.DELETE)
    public @ResponseBody void removeSaleable(@PathVariable Long packageId, @PathVariable Long saleableId) {

        SalePackage salePackage = SalePackageBuilder.createPackage(packageId).build();
        SaleableUnit saleableUnit = SaleableBuilder.createSaleable(saleableId).build();

        this.service.removeSaleable(salePackage, saleableUnit);
    }

    @RequestMapping(value="/sales-package/create")
    public ModelAndView newProduct() {

        return new ModelAndView("/saleables/packages/packageEdit");
    }

}
