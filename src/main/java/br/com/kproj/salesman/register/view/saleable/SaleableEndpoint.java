package br.com.kproj.salesman.register.view.saleable;

import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnit;
import br.com.kproj.salesman.register.application.contract.saleable.SaleableApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
public class SaleableEndpoint {

    @Autowired
    private SaleableApplication service;



    @RequestMapping(value = "/rs/saleable/{saleableId}", method = RequestMethod.GET)
    public @ResponseBody
    SaleableUnit getSaleable(@PathVariable Long saleableId) {

        Optional<SaleableUnit> saleable = service.getOne(saleableId);

        return saleable.isPresent() ? saleable.get() : null;
    }

}
