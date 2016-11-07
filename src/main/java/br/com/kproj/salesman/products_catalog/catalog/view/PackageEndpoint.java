package br.com.kproj.salesman.products_catalog.catalog.view;

import br.com.kproj.salesman.infrastructure.exceptions.NotFoundException;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItems;
import br.com.kproj.salesman.infrastructure.repository.Pager;
import br.com.kproj.salesman.products_catalog.catalog.application.SalePackageFacade;
import br.com.kproj.salesman.products_catalog.catalog.domain.model.saleables.SaleableUnit;
import br.com.kproj.salesman.products_catalog.catalog.domain.model.salepackage.SalePackage;
import br.com.kproj.salesman.products_catalog.catalog.domain.model.salepackage.SaleableRelation;
import br.com.kproj.salesman.products_catalog.catalog.view.support.builders.SalePackageResourceBuilder;
import br.com.kproj.salesman.products_catalog.catalog.view.support.resources.SaleableResource;
import com.google.common.collect.Iterables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static com.google.common.collect.FluentIterable.from;


@RestController
public class PackageEndpoint {


    private SalePackageFacade service;

    private SalePackageResourceBuilder builder;


    @Autowired
    public PackageEndpoint(SalePackageFacade service, SalePackageResourceBuilder builder) {
        this.service = service;
        this.builder = builder;
    }


    @RequestMapping(value = "/rs/saleables/packages/{packageId}", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItem getPackage(HttpServletRequest request, @PathVariable Long packageId) {
        Optional<SalePackage> result = service.getOne(packageId);

        SalePackage salePackage = result.orElseThrow(() -> new NotFoundException());

        return builder.build(salePackage, request.getRequestURI());
    }

    @RequestMapping(value = "/rs/saleables/packages", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItems getServices(HttpServletRequest request) {
        Iterable<SalePackage> salePackages = service.findAll(Pager.build().withPageNumer(10000));

        if(Iterables.isEmpty(salePackages)) {
            throw new NotFoundException();
        }

        return builder.build(from(salePackages).toList(), request.getRequestURI());
    }

    @RequestMapping(value = "/rs/saleables/{saleableId}/packages", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    ResourceItem create(@PathVariable Long saleableId, HttpServletRequest request) {

        SalePackage salePackage = new SalePackage(saleableId);

        Optional<SalePackage> packageCreated = service.register(salePackage);

        return builder.build(packageCreated.get(), request.getRequestURI());
    }

    @RequestMapping(value = "/rs/saleables/packages/{packageId}/relations", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    ResourceItem addSaleable(@PathVariable Long packageId, @RequestBody SaleableResource saleable, HttpServletRequest request) {
        SalePackage salePackage = new SalePackage(packageId);

        service.addSaleable(salePackage, new SaleableUnit(saleable.getId()));

        Optional<SalePackage> packageCreated = service.getOne(packageId);

        return builder.build(packageCreated.get(), request.getRequestURI());
    }

    @RequestMapping(value = "/rs/saleables/packages/relations/{relationId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody void removeSaleable(@PathVariable Long relationId) {

        service.removeSaleable(new SaleableRelation(relationId));

    }



}
