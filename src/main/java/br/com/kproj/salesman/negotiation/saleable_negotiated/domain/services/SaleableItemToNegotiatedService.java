package br.com.kproj.salesman.negotiation.saleable_negotiated.domain.services;

import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.negotiated.Negotiated;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleables_items.SaleableItem;

@FunctionalInterface
public interface SaleableItemToNegotiatedService {

    SaleableItem to(Negotiated negotiated);
}
