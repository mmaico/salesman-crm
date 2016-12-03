package br.com.kproj.salesman.negotiation.saleable_negotiated.application.impl;

import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import br.com.kproj.salesman.negotiation.saleable_negotiated.application.SaleableItemFacade;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.negotiated.Negotiated;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleables_items.SaleableItem;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleables_items.SaleableItemRepository;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleables_items.SaleableItemToNegotiated;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleables_items.SaleableItemValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.seller.Seller.seller;

@Service
public class SaleableItemServiceImpl extends BaseModelServiceImpl<SaleableItem> implements SaleableItemFacade {

    private SaleableItemRepository repository;
    private SaleableItemValidate validate;

    @Autowired
    public SaleableItemServiceImpl(SaleableItemRepository repository, SaleableItemValidate validate) {
        this.repository = repository;
        this.validate = validate;
    }

    @Override
    public void delete(SaleableItem saleableItem) {

        if (saleableItem.isNew()) {
            throw new ValidationException("negotiation.negotiated.saleable.item.invalid.delete");
        }
        repository.delete(saleableItem);
    }

    @Override
    public SaleableItem register(SaleableItemToNegotiated saleableToNegotiated) {
        SaleableItem saleableItem = saleableToNegotiated.getSaleableItem();
        Negotiated negotiated = saleableToNegotiated.getNegotiated();
        saleableItem.setNegotiated(negotiated);

        validate.checkRules(saleableItem);
        return seller().save(saleableItem).to(negotiated);
    }

    @Override
    public BaseRepository<SaleableItem, Long> getRepository() {
        return repository;
    }


}
