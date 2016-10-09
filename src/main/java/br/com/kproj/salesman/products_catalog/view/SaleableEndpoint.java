package br.com.kproj.salesman.products_catalog.view;

import br.com.kproj.salesman.infrastructure.http.response.handler.annotation.ResourceWrapper;
import br.com.kproj.salesman.infrastructure.repository.Pager;
import br.com.kproj.salesman.products_catalog.application.SaleableUnitFacade;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.SaleableUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;


@RestController
public class SaleableEndpoint {

    @Autowired
    private SaleableUnitFacade service;


    @ResourceWrapper
    @RequestMapping(value = "/rs/saleables", method = RequestMethod.GET)
    public @ResponseBody
    Collection<SaleableUnit> getSaleables() {
        Page<SaleableUnit> result = (Page<SaleableUnit>) service.findAll(Pager.build().withPageSize(1000));
        return result.getContent();
    }



}
