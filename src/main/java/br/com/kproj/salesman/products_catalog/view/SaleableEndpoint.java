package br.com.kproj.salesman.products_catalog.view;

import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.products_catalog.application.SaleableUnitFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class SaleableEndpoint {

    @Autowired
    private SaleableUnitFacade service;



    @RequestMapping(value = "/rs/saleable/{saleableId}", method = RequestMethod.GET)
    public @ResponseBody
    SaleableUnitEntity getSaleable(@PathVariable Long saleableId) {

        //Optional<SaleableUnitEntity> saleable = service.getOne(saleableId);

        return null; //saleable.isPresent() ? saleable.get() : null;
    }



}
