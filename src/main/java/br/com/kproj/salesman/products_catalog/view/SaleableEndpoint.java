package br.com.kproj.salesman.products_catalog.view;

import br.com.kproj.salesman.infrastructure.exceptions.NotFoundException;
import br.com.kproj.salesman.infrastructure.http.response.handler.annotation.ResourceWrapper;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItems;
import br.com.kproj.salesman.infrastructure.repository.Pager;
import br.com.kproj.salesman.products_catalog.application.SaleableUnitFacade;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.SaleableBuilder;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.SaleableUnit;
import br.com.kproj.salesman.products_catalog.view.support.builders.SaleableResourceBuilder;
import br.com.kproj.salesman.products_catalog.view.support.builders.SaleableStrategyBuilder;
import br.com.kproj.salesman.products_catalog.view.support.dtos.SaleableDTO;
import br.com.kproj.salesman.products_catalog.view.support.resources.SaleableResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;


@RestController
public class SaleableEndpoint {


    private SaleableUnitFacade service;

    private SaleableResourceBuilder builder;

    @Autowired
    public SaleableEndpoint(SaleableUnitFacade service, SaleableResourceBuilder builder) {
        this.service = service;
        this.builder = builder;
    }

    @RequestMapping(value = "/rs/saleables", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItems getSaleables(HttpServletRequest request) {
        Page<SaleableUnit> result = (Page<SaleableUnit>) service.findAll(Pager.build().withPageSize(1000));

        if (result.getContent().isEmpty()) {
            throw new NotFoundException();
        }

        return builder.build(result.getContent(), request.getRequestURI());
    }

    @RequestMapping(value = "/rs/saleables/{saleableId}", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItem getSaleableById(@PathVariable Long saleableId, HttpServletRequest request) {
        Optional<SaleableUnit> result = service.getOne(saleableId);
        SaleableUnit saleableUnit = result.orElseThrow(() -> new NotFoundException());

        return builder.build(saleableUnit, request.getRequestURI());
    }

    @ResourceWrapper
    @RequestMapping(value = "/rs/saleables", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    Optional<SaleableUnit> create(@Valid @RequestBody SaleableResource resource) {

        SaleableUnit saleable = SaleableBuilder.createSaleable()
                .withPrice(resource.getPrice())
                .withPriceCost(resource.getPriceCost())
                .withActive(resource.getActive())
                .withName(resource.getName())
                .withDescription(resource.getDescription()).build();

        Optional<SaleableUnit> saleableSaved = service.register(saleable);

        return saleableSaved;
    }

    @ResourceWrapper
    @RequestMapping(value = "/rs/saleables", method = RequestMethod.PUT)
    public @ResponseBody
    Optional<SaleableUnit> update(@Valid @RequestBody SaleableDTO saleableDTO) {
        SaleableUnit saleableUnit = SaleableStrategyBuilder.build(saleableDTO);
        Optional<SaleableUnit> saleableSaved = service.register(saleableUnit);

        return saleableSaved;
    }


}
