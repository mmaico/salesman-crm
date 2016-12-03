package br.com.kproj.salesman.negotiation.saleable_negotiated.application;

import br.com.kproj.salesman.infrastructure.service.ModelFacade;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleables_items.SaleableItem;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleables_items.SaleableItemToNegotiated;

public interface SaleableItemFacade extends ModelFacade<SaleableItem> {


    void delete(SaleableItem saleableItem);

    SaleableItem register(SaleableItemToNegotiated saleableToNegotiated);

}
