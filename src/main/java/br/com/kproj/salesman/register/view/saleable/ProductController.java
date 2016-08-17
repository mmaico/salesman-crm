package br.com.kproj.salesman.register.view.saleable;

import br.com.kproj.salesman.infrastructure.entity.saleable.ProductEntity;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.helpers.NormalizeEntityRequest;
import br.com.kproj.salesman.infrastructure.repository.Pager;
import br.com.kproj.salesman.register.application.contract.saleable.ProductApplication;
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


@RestController(value = "oldProductController")
public class ProductController {

//    @Autowired
//    private ProductApplication service;
//
//    @Autowired
//    private SaleableValidator validator;
//
//    @Autowired
//    private NormalizeEntityRequest normalizeEntityRequest;
//
//    @InitBinder(value = {"product"})
//    private void initBinder(WebDataBinder binder) {
//        binder.setValidator(validator);
//
//
//    }
//
//    @RequestMapping(value = "/products/save", method = RequestMethod.POST)
//    public @ResponseBody
//    SaleableUnitEntity save(@ModelAttribute @Validated ProductEntity product, BindingResult bindingResult, Model model) {
//
//        if (bindingResult.hasErrors()) {
//            throw new ValidationException(bindingResult.getAllErrors());
//        }
//
//        SaleableUnitEntity saleable = service.register(product);
//
//        return saleable;
//    }
//
//    @RequestMapping(value = "/products/save", method = RequestMethod.PUT)
//    public @ResponseBody
//    void update(@ModelAttribute ProductEntity product) {
//
//        normalizeEntityRequest.addFieldsToUpdate(product);
//        service.register(product);
//    }
//
//    @RequestMapping("/products/list")
//    public ModelAndView list(@PageableDefault(page=0, size=150000)Pageable pageable, Model model) {
//
//        Pager pager = Pager.binding(pageable);
//
//        Iterable<ProductEntity> result = this.service.findAll(pager);
//
//        model.addAttribute("products", result);
//        return new ModelAndView("/saleables/products/productList");
//    }
//
//    @RequestMapping(value="/products/{productId}")
//    public ModelAndView viewInfo(@PathVariable Long productId, Model model) {
//
//        Optional<ProductEntity> result = this.service.getOne(productId);
//
//        model.addAttribute("product", result.isPresent() ? result.get(): null);
//        return new ModelAndView("/saleables/products/productDetail");
//    }
//
//
//    @RequestMapping(value="/products/create")
//    public ModelAndView newProduct() {
//
//        return new ModelAndView("/saleables/products/productEdit");
//    }

}
