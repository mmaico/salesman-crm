package br.com.kproj.salesman.negotiation.saleable_negotiated.view;

import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.kproj.salesman.negotiation.saleable_negotiated.application.SaleableItemFacade;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleables_items.SaleableItem;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleables_items.SaleableItemBuilder;
import br.com.kproj.salesman.negotiation.saleable_negotiated.view.support.builders.SaleableItemResourceBuilder;
import br.com.kproj.salesman.negotiation.saleable_negotiated.view.support.resources.SaleableItemResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleables_items.SaleableItemToNegotiated.createToNegotiated;

@RestController
public class SaleableItemEndpoint {

    private SaleableItemFacade service;

    private SaleableItemResourceBuilder builder;

    @Autowired
    public SaleableItemEndpoint(SaleableItemFacade service, SaleableItemResourceBuilder builder) {
        this.service = service;
        this.builder = builder;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/rs/customers/negotiations/negotiated-items/saleables-items/{saleableItemId}", method = RequestMethod.DELETE)
    public @ResponseBody
    void delete(@PathVariable Long saleableItemId) {
        service.delete(new SaleableItem(saleableItemId));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/rs/customers/negotiations/negotiated-items/{negotiatedId}/saleables-items", method = RequestMethod.POST)
    public @ResponseBody
    ResourceItem create(@PathVariable Long negotiatedId, @RequestBody SaleableItemResource resource) {

        SaleableItem saleableItem = SaleableItemBuilder
                .createSaleableItem()
                .withSaleable(resource.getSaleableId())
                .withUsedPackage(resource.getUsedPackageId()).build();

        SaleableItem saleableItemSaved = service.register(createToNegotiated(saleableItem, negotiatedId));

        return builder.build(saleableItemSaved);
    }

}
