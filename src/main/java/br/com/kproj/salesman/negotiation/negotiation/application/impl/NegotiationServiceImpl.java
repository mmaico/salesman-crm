package br.com.kproj.salesman.negotiation.negotiation.application.impl;

import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import br.com.kproj.salesman.negotiation.negotiation.application.NegotiationFacade;
import br.com.kproj.salesman.negotiation.negotiation.domain.model.customer.Customer;
import br.com.kproj.salesman.negotiation.negotiation.domain.model.negotiation.Negotiation;
import br.com.kproj.salesman.negotiation.negotiation.domain.model.negotiation.NegotiationRepository;
import br.com.kproj.salesman.negotiation.negotiation.domain.model.negotiation.NegotiationValidate;
import br.com.kproj.salesman.negotiation.negotiation.domain.model.negotiation.Temperature;
import br.com.kproj.salesman.negotiation.negotiation.domain.model.seller.Seller;
import br.com.kproj.salesman.negotiation.negotiation.domain.model.seller.SellerSaveNegotiation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

import static br.com.kproj.salesman.negotiation.negotiation.domain.model.seller.Seller.seller;

@Service
public class NegotiationServiceImpl extends BaseModelServiceImpl<Negotiation> implements NegotiationFacade {


    private NegotiationRepository repository;
    private NegotiationValidate negotiationBusinessRules;

//    @Autowired
//    private NegotiationEventHandler eventHandler;


    @Autowired
    public NegotiationServiceImpl(NegotiationRepository repository, NegotiationValidate negotiationBusinessRules) {
        this.repository = repository;
        this.negotiationBusinessRules = negotiationBusinessRules;
    }


    @Override
    public Optional<Negotiation> register(SellerSaveNegotiation saveNegotiation) {
        Negotiation negotiation = saveNegotiation.getNegotiation();
        Seller seller = saveNegotiation.getSeller();
        negotiation.setSellerOnlyHaveId(seller);

        negotiationBusinessRules.checkRules(negotiation);

        return seller.save(negotiation).withInitial(Temperature.COLD);
    }

    @Override
    public Negotiation update(Negotiation negotiation) {
        negotiationBusinessRules.checkRules(negotiation);

        return seller().update(negotiation);
    }

    @Override
    public Collection<Negotiation> findOne(Customer customer) {
        return repository.findOne(customer);
    }

//    @Override
//    public void changeTemperature(Seller seller, NegotiationChangeTemperature negotiationToChange) {
//
//        businessRules.isValidBusinessRulesFor(seller, negotiationToChange);
//        Negotiation negotiation = negotiationToChange.getNegotiation();
//        Temperature newTemperature = negotiationToChange.getNewTemperature();
//
//        seller.changeTemperature(newTemperature).from(negotiation);
//
//        if (negotiation.temperatureWasClosedWon()) {
//            eventHandler.negotiationClosedWon(negotiation);
//        }
//    }

    @Override
    public BaseRepository<Negotiation, Long> getRepository() {
        return repository;
    }

}
