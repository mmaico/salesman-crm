package br.com.kproj.salesman.negotiation.application.impl;

import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import br.com.kproj.salesman.negotiation.application.NegotiationFacade;
import br.com.kproj.salesman.negotiation.domain.model.account.Account;
import br.com.kproj.salesman.negotiation.domain.model.negotiation.*;
import br.com.kproj.salesman.negotiation.domain.model.seller.Seller;
import br.com.kproj.salesman.negotiation.domain.model.seller.SellerRepository;
import br.com.kproj.salesman.negotiation.domain.model.seller.SellerSaveNegotiation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class NegotiationServiceImpl extends BaseModelServiceImpl<Negotiation> implements NegotiationFacade {

    @Autowired
    private NegotiationRepository repository;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    @Qualifier("rulesToChangeTemperature")
    private RulesToChangeTemperature businessRules;

    @Autowired
    private NegotiationEventHandler eventHandler;

    @Autowired
    private NegotiationValidate negotiationBusinessRules;


    @Override
    public Optional<Negotiation> register(SellerSaveNegotiation saveNegotiation) {
        Negotiation negotiation = saveNegotiation.getNegotiation();
        Seller seller = saveNegotiation.getSeller();

        negotiationBusinessRules.checkRules(negotiation);

        return seller.save(negotiation).whenNewUse(Temperature.COLD);
    }

    @Override
    public Collection<Negotiation> findOne(Account account) {
        return repository.findOne(account);
    }

    @Override
    public void changeTemperature(Seller seller, NegotiationChangeTemperature negotiationToChange) {

        businessRules.isValidBusinessRulesFor(seller, negotiationToChange);
        Negotiation negotiation = negotiationToChange.getNegotiation();
        Temperature newTemperature = negotiationToChange.getNewTemperature();

        seller.changeTemperature(newTemperature).from(negotiation);

        if (negotiation.temperatureWasClosedWon()) {
            eventHandler.negotiationClosedWon(negotiation);
        }
    }

    @Override
    public BaseRepository<Negotiation, Long> getRepository() {
        return repository;
    }

}
