package br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleables_items;

import br.com.kproj.salesman.infrastructure.model.ValueObject;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.negotiated.Negotiated;


public class SaleableItemToNegotiated implements ValueObject {

    private final SaleableItem saleableItem;
    private final Long negotiatedId;

    public SaleableItemToNegotiated(SaleableItem saleableItem, Long negotiatedId) {
        this.saleableItem = saleableItem;
        this.negotiatedId = negotiatedId;
    }

    public SaleableItem getSaleableItem() {
        return saleableItem;
    }

    public Negotiated getNegotiated() {
        return new Negotiated(negotiatedId);
    }

    public static SaleableItemToNegotiated createToNegotiated(SaleableItem saleableItem, Long negotiatedId) {
        return new SaleableItemToNegotiated(saleableItem, negotiatedId);
    }
}
