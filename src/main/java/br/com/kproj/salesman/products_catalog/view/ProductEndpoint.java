package br.com.kproj.salesman.products_catalog.view;

import br.com.kproj.salesman.infrastructure.exceptions.NotFoundException;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItems;
import br.com.kproj.salesman.infrastructure.repository.Pager;
import br.com.kproj.salesman.products_catalog.application.ProductFacade;
import br.com.kproj.salesman.products_catalog.domain.model.products.Product;
import br.com.kproj.salesman.products_catalog.view.support.builders.ProductResourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
public class ProductEndpoint {


    private ProductFacade service;

    private ProductResourceBuilder builder;

    @Autowired
    public ProductEndpoint(ProductFacade service, ProductResourceBuilder builder) {
        this.service = service;
        this.builder = builder;
    }

    @RequestMapping(value = "/rs/saleables/products/{productId}", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItems getProducts(HttpServletRequest request, @PathVariable Long productId) {
        Page<Product> result = (Page<Product>) service.findAll(Pager.build().withPageSize(1000));

        if (result.getContent().isEmpty()) {
            throw new NotFoundException();
        }

        return builder.build(result.getContent(), request.getRequestURI());
    }

//    @RequestMapping(value = "/rs/saleables/products/{productId}", method = RequestMethod.GET)
//    public @ResponseBody
//    ResourceItem getSaleableById(@PathVariable Long saleableId, HttpServletRequest request) {
//        Optional<SaleableUnit> result = service.getOne(saleableId);
//        SaleableUnit saleableUnit = result.orElseThrow(() -> new NotFoundException());
//
//        return builder.build(saleableUnit, request.getRequestURI());
//    }
//
//    @ResourceWrapper
//    @RequestMapping(value = "/rs/saleables/{saleableId}", method = RequestMethod.POST)
//    @ResponseStatus(HttpStatus.CREATED)
//    public @ResponseBody
//    Optional<Product> create(@Valid @RequestBody ProductResource resource) {
//
//        //Optional<SaleableUnit> saleableSaved = service.register(saleable);
//
//        return Optional.empty();
//    }
//
//    @ResourceWrapper
//    @RequestMapping(value = "/rs/saleables", method = RequestMethod.PUT)
//    public @ResponseBody
//    Optional<SaleableUnit> update(@Valid @RequestBody SaleableDTO saleableDTO) {
//        SaleableUnit saleableUnit = SaleableStrategyBuilder.build(saleableDTO);
//        Optional<SaleableUnit> saleableSaved = service.register(saleableUnit);
//
//        return saleableSaved;
//    }


}
