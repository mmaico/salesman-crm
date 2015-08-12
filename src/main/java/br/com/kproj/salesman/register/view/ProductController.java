package br.com.kproj.salesman.register.view;

import br.com.kproj.salesman.infrastructure.entity.Product;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.helpers.NormalizeEntityRequest;
import br.com.kproj.salesman.infrastructure.repository.Pager;
import br.com.kproj.salesman.register.application.ProductService;
import br.com.kproj.salesman.register.infrastructure.validators.ProductValidator;


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
public class ProductController {

    @Autowired
    private ProductService service;

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
    ResponseEntity save(@ModelAttribute @Validated Product product, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors());
        }
        normalizeEntityRequest.addFieldsToUpdate(product);
        service.register(product);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/products/save", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity update(@ModelAttribute @Validated Product product, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors());
        }
        normalizeEntityRequest.addFieldsToUpdate(product);
        service.register(product);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping("/products/list")
    public ModelAndView list(@PageableDefault(page=0, size=15)Pageable pageable, Model model) {

        Pager pager = Pager.binding(pageable);

        Iterable<Product> result = this.service.findAll(pager);

        model.addAttribute("products", result);
        return new ModelAndView("/products/list");
    }
    
    @RequestMapping(value="/products/{productId}")
    public ModelAndView viewInfo(Long productId, Model model) {
        
        Optional<Product> result = this.service.getOne(productId);

        model.addAttribute("product", result.get());
        return new ModelAndView("/products/edit");
    }

    @RequestMapping(value="/products/create")
    public ModelAndView newProduct() {

        return new ModelAndView("/products/newProduct");
    }

}
