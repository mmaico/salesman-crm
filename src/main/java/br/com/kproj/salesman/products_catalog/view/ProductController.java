package br.com.kproj.salesman.products_catalog.view;

import br.com.kproj.salesman.infrastructure.helpers.view.NormalizeAttrUpdateHelper;
import br.com.kproj.salesman.infrastructure.repository.Pager;
import br.com.kproj.salesman.products_catalog.application.ProductFacade;
import br.com.kproj.salesman.products_catalog.domain.model.products.Product;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.SaleableValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;


@RestController
public class ProductController {

    @Autowired
    private ProductFacade service;

    @Autowired
    @Qualifier("saleableViewValidator")
    private SaleableValidator validator;

    @Autowired
    private NormalizeAttrUpdateHelper attributesToUpdate;


    @RequestMapping(value = "/products/add", method = RequestMethod.POST)
    public @ResponseBody Product save(@ModelAttribute Product product) {

        validator.checkRules(product);

        Optional<Product> saleable = service.register(product);

        return saleable.isPresent() ? saleable.get() : null;
    }

    @RequestMapping(value = "/products/add", method = RequestMethod.PUT)
    public @ResponseBody void update(@ModelAttribute Product product) {

        attributesToUpdate.addAttributesToUpdate(product);
        service.register(product);
    }

    @RequestMapping("/products/list")
    public ModelAndView list(@PageableDefault(size=150000)Pageable pageable, Model model) {

        Pager pager = Pager.binding(pageable);

        Iterable<Product> result = this.service.findAll(pager);

        model.addAttribute("products", result);
        return new ModelAndView("/saleables/products/productList");
    }
    
    @RequestMapping(value="/products/{productId}")
    public ModelAndView viewInfo(@PathVariable Long productId, Model model) {
        
        Optional<Product> result = this.service.getOne(productId);

        model.addAttribute("product", result.isPresent() ? result.get(): null);
        return new ModelAndView("/saleables/products/productDetail");
    }


    @RequestMapping(value="/products/create")
    public ModelAndView newProduct() {

        return new ModelAndView("/saleables/products/productEdit");
    }

}
