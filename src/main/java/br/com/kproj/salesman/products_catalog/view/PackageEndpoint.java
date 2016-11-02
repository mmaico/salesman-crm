package br.com.kproj.salesman.products_catalog.view;

import br.com.kproj.salesman.infrastructure.exceptions.NotFoundException;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItems;
import br.com.kproj.salesman.infrastructure.repository.Pager;
import br.com.kproj.salesman.products_catalog.application.SalePackageFacade;
import br.com.kproj.salesman.products_catalog.domain.model.salepackage.SalePackage;
import br.com.kproj.salesman.products_catalog.view.support.builders.SalePackageResourceBuilder;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
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
