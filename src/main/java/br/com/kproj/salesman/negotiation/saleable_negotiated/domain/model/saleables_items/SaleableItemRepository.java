package br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleables_items;


import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.negotiated.Negotiated;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleable.Saleable;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleable.SaleablePackage;

import java.util.Collection;
import java.util.List;

public interface SaleableItemRepository extends BaseRepository<SaleableItem, Long> {


    List<SaleableItem> generateBy(Saleable saleable, Negotiated negotiated);

    Collection<SaleableItem> findAll(Negotiated negotiated);

    void delete(SaleableItem saleableItem);

    Boolean alreadyExists(Negotiated negotiated, Saleable saleable, SaleablePackage saleablePackage);

}

