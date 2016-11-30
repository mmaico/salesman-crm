package br.com.kproj.salesman.negotiation.saleable_negotiated.application.impl;

import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import br.com.kproj.salesman.negotiation.saleable_negotiated.application.SaleableItemFacade;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleables_items.SaleableItem;
import org.springframework.stereotype.Service;

@Service
public class SaleableItemServiceImpl extends BaseModelServiceImpl<SaleableItem> implements SaleableItemFacade {


//    private NegotiationRepository repository;
//    private NegotiationValidate negotiationBusinessRules;
//
////    @Autowired
////    private NegotiationEventHandler eventHandler;
//
//
//    @Autowired
//    public SaleableItemServiceImpl(NegotiationRepository repository, NegotiationValidate negotiationBusinessRules) {
//        this.repository = repository;
//        this.negotiationBusinessRules = negotiationBusinessRules;
//    }


//    @Override
//    public Optional<Negotiation> register(SellerSaveNegotiation saveNegotiation) {
//        Negotiation negotiation = saveNegotiation.getNegotiation();
//        Seller seller = saveNegotiation.getSeller();
//        negotiation.setSellerOnlyHaveId(seller);
//
//        negotiationBusinessRules.checkRules(negotiation);
//
//        return seller.save(negotiation).withInitial(Temperature.COLD);
//    }
//
//    @Override
//    public Negotiation update(Negotiation negotiation) {
//        negotiationBusinessRules.checkRules(negotiation);
//
//        return seller().update(negotiation);
//    }


    @Override
    public BaseRepository<SaleableItem, Long> getRepository() {
        return null;
    }

}
