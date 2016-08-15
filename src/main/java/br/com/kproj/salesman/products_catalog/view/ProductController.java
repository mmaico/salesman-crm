package br.com.kproj.salesman.products_catalog.view;

import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.helpers.NormalizeEntityRequest;
import br.com.kproj.salesman.infrastructure.repository.Pager;
import br.com.kproj.salesman.products_catalog.application.ProductFacade;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.Product;
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
public class ProductController {

    @Autowired
    private ProductFacade service;

    @Autowired
    private SaleableValidator validator;

    @Autowired
    private NormalizeEntityRequest normalizeEntityRequest;

    @InitBinder(value = {"product"})
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);

        
    }

    @RequestMapping(value = "/products/save", method = RequestMethod.POST)
    public @ResponseBody
    Product save(@ModelAttribute @Validated Product product, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors());
        }

        Product saleable = null; //service.register(product);

        return saleable;
    }

    @RequestMapping(value = "/products/save", method = RequestMethod.PUT)
    public @ResponseBody
    void update(@ModelAttribute Product product) {

        //normalizeEntityRequest.addFieldsToUpdate(product);
        //service.register(product);
    }

    @RequestMapping("/products/list")
    public ModelAndView list(@PageableDefault(size=150000)Pageable pageable, Model model) {

        Pager pager = Pager.binding(pageable);

        Iterable<Product> result = null; //this.service.findAll(pager);

        model.addAttribute("products", result);
        return new ModelAndView("/saleables/products/productList");
    }
    
    @RequestMapping(value="/products/{productId}")
    public ModelAndView viewInfo(@PathVariable Long productId, Model model) {
        
        Optional<Product> result = null;//this.service.getOne(productId);

        model.addAttribute("product", result.isPresent() ? result.get(): null);
        return new ModelAndView("/saleables/products/productDetail");
    }


    @RequestMapping(value="/products/create")
    public ModelAndView newProduct() {

        return new ModelAndView("/saleables/products/productEdit");
    }

}
