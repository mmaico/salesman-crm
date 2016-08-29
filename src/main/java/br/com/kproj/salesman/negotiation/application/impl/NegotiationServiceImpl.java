package br.com.kproj.salesman.negotiation.application.impl;

import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import br.com.kproj.salesman.negotiation.application.NegotiationFacade;
import br.com.kproj.salesman.negotiation.domain.model.account.Account;
import br.com.kproj.salesman.negotiation.domain.model.negotiation.*;
import br.com.kproj.salesman.negotiation.domain.model.seller.Seller;
import br.com.kproj.salesman.negotiation.domain.model.seller.SellerRepository;
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
    public Optional<Negotiation> register(Negotiation negotiation) {
        negotiationBusinessRules.checkRules(negotiation);

        if (negotiation.isNew()) {
            negotiation.useInitialTemperature();
            return getRepository().save(negotiation);
        } else {
            getRepository().save(negotiation);
        }

        return Optional.empty();
    }

    @Override
    public Collection<Negotiation> findOne(Account account) {
        return repository.findOne(account);
    }

    @Override
    public void changeTemperature(Seller sellerWillChange, NegotiationChangeTemperature negotiationToChange) {

        businessRules.isValidBusinessRulesFor(sellerWillChange, negotiationToChange);

        Seller seller = sellerRepository.findOne(sellerWillChange.getId()).get();

        seller.changeTemperatureFrom(negotiationToChange);

        Negotiation negotiation = repository.findOne(negotiationToChange.getNegotiation().getId()).get();

        if (negotiation.temperatureWasClosedWon()) {
            eventHandler.negotiationClosedWon(negotiation);
        }
    }

    @Override
    public BaseRepository<Negotiation, Long> getRepository() {
        return repository;
    }

}
