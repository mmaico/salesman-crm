package br.com.kproj.salesman.products_catalog.view;

import br.com.kproj.salesman.infrastructure.http.response.handler.annotation.ResourceWrapper;
import br.com.kproj.salesman.infrastructure.repository.Pager;
import br.com.kproj.salesman.products_catalog.application.SaleableUnitFacade;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.SaleableUnit;
import br.com.kproj.salesman.products_catalog.view.support.builders.SaleableStrategyBuilder;
import br.com.kproj.salesman.products_catalog.view.support.dtos.SaleableDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;


@RestController
public class SaleableEndpoint {


    private SaleableUnitFacade service;

    @Autowired
    public SaleableEndpoint(SaleableUnitFacade service) {
        this.service = service;
    }

    @ResourceWrapper
    @RequestMapping(value = "/rs/saleables", method = RequestMethod.GET)
    public @ResponseBody
    Collection<SaleableUnit> getSaleables() {
        Page<SaleableUnit> result = (Page<SaleableUnit>) service.findAll(Pager.build().withPageSize(1000));
        return result.getContent();
    }

    @ResourceWrapper
    @RequestMapping(value = "/rs/saleables/{saleableId}", method = RequestMethod.GET)
    public @ResponseBody
    Optional<SaleableUnit> getSaleableById(@PathVariable Long saleableId) {
        Optional<SaleableUnit> result = service.getOne(saleableId);

        return service.getOne(saleableId);
    }

    @ResourceWrapper
    @RequestMapping(value = "/rs/saleables", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    Optional<SaleableUnit> create(@Valid @RequestBody SaleableDTO saleableDTO) {
        SaleableUnit saleableUnit = SaleableStrategyBuilder.build(saleableDTO);
        Optional<SaleableUnit> saleableSaved = service.register(saleableUnit);

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
