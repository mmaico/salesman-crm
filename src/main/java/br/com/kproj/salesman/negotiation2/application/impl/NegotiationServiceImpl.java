package br.com.kproj.salesman.negotiation2.application.impl;

import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import br.com.kproj.salesman.negotiation2.application.NegotiationFacade;
import br.com.kproj.salesman.negotiation2.domain.model.account.Account;
import br.com.kproj.salesman.negotiation2.domain.model.negotiation.Negotiation;
import br.com.kproj.salesman.negotiation2.domain.model.negotiation.NegotiationRepository;
import br.com.kproj.salesman.negotiation2.domain.model.negotiation.NegotiationToChangeTemperature;
import br.com.kproj.salesman.negotiation2.domain.model.seller.Seller;
import br.com.kproj.salesman.negotiation2.domain.model.seller.SellerRepository;
import br.com.kproj.salesman.products_catalog.application.ProductFacade;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.Product;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.ProductRepository;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.SaleableValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class NegotiationServiceImpl extends BaseModelServiceImpl<Negotiation> implements NegotiationFacade {

    @Autowired
    private NegotiationRepository repository;

    @Autowired
    private SellerRepository sellerRepository;


    @Override
    public Optional<Negotiation> register(Negotiation negotiation) {


        return Optional.empty();
    }

    @Override
    public Collection<Negotiation> find(Account account) {
        return repository.find(account);
    }

    @Override
    public void changeTemperature(Seller seller, NegotiationToChangeTemperature negotiation) {
        Optional<Seller> sellerFound = sellerRepository.findOne(seller.getId());


        sellerFound.get().changeTemperatureFrom(negotiation);

        Optional<Negotiation> negotiationFound = repository.findOne(negotiation.getNegotiation().getId());

        if (negotiationFound.get().temperatureIsClosedWon()) {
            //Send event
        }

    }

    @Override
    public BaseRepository<Negotiation, Long> getRepository() {
        return repository;
    }

}
