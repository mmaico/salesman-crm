package br.com.kproj.salesman.negotiation.saleable_negotiated.domain.services;

import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleables_items.GenerateSaleableItems;

@FunctionalInterface
public interface GenerateSaleableItemsService {

    NegotiatedToNegotiationService andGenerate(GenerateSaleableItems generate);
}
