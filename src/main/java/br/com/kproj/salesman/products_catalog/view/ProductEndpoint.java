package br.com.kproj.salesman.products_catalog.view;

import br.com.kproj.salesman.infrastructure.exceptions.NotFoundException;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItems;
import br.com.kproj.salesman.infrastructure.repository.Pager;
import br.com.kproj.salesman.products_catalog.application.ProductFacade;
import br.com.kproj.salesman.products_catalog.domain.model.products.Product;
import br.com.kproj.salesman.products_catalog.view.support.builders.ProductResourceBuilder;
import br.com.kproj.salesman.products_catalog.view.support.resources.ProductResource;
import com.google.common.collect.Iterables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static br.com.kproj.salesman.products_catalog.domain.model.products.ProductBuilder.createProduct;
import static com.google.common.collect.FluentIterable.from;


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
    ResourceItem getProduct(HttpServletRequest request, @PathVariable Long productId) {
        Optional<Product> result = service.getOne(productId);

        Product product = result.orElseThrow(() -> new NotFoundException());

        return builder.build(product, request.getRequestURI());
    }

    @RequestMapping(value = "/rs/saleables/products", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItems getProducts(HttpServletRequest request) {
        Iterable<Product> products = service.findAll(Pager.build().withPageNumer(10000));

        if(Iterables.isEmpty(products)) {
            throw new NotFoundException();
        }

        return builder.build(from(products).toList(), request.getRequestURI());
    }

    @RequestMapping(value = "/rs/saleables/{saleableId}/products", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    ResourceItem create(@PathVariable Long saleableId, @RequestBody ProductResource resource,
                        HttpServletRequest request) {

        Product product = createProduct(saleableId)
                .withUnit(resource.getUnit().getId()).build();

        Optional<Product> productCreated = service.register(product);

        return builder.build(productCreated.get(), request.getRequestURI());
    }
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
