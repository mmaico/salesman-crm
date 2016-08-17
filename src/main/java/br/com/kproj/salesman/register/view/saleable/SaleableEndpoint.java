package br.com.kproj.salesman.register.view.saleable;

import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.register.application.contract.saleable.SaleableApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController(value = "oldSaleableEndpoint")
public class SaleableEndpoint {

//    @Autowired
//    private SaleableApplication service;
//
//
//
//    @RequestMapping(value = "/rs/saleable/{saleableId}", method = RequestMethod.GET)
//    public @ResponseBody
//    SaleableUnitEntity getSaleable(@PathVariable Long saleableId) {
//
//        Optional<SaleableUnitEntity> saleable = service.getOne(saleableId);
//
//        return saleable.isPresent() ? saleable.get() : null;
//    }
//


}
