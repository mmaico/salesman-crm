package br.com.kproj.salesman.negotiation.saleable_negotiated.application.impl;

import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import br.com.kproj.salesman.negotiation.saleable_negotiated.application.SaleableNegotiatedFacade;
import br.com.kproj.salesman.negotiation.saleable_negotiated.application.validators.NegotiatedRulesDescription;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.negotiated.Negotiated;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.negotiated.NegotiatedInNegotiation;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.negotiated.NegotiatedRepository;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.negotiated.NegotiatedValidate;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.negotiation.Negotiation;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleable.Saleable;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleables_items.GenerateSaleableItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

import static br.com.kproj.salesman.negotiation.saleable_negotiated.application.validators.NegotiatedRulesDescription.ignoreRules;
import static br.com.kproj.salesman.negotiation.saleable_negotiated.application.validators.NegotiatedRulesDescription.rulePackage;
import static br.com.kproj.salesman.negotiation.saleable_negotiated.application.validators.NegotiatedRulesDescription.ruleSaleable;
import static br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleables_items.GenerateSaleableItems.generateSaleableItems;
import static br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.seller.Seller.seller;

@Service
public class NegotiatedServiceImpl extends BaseModelServiceImpl<Negotiated> implements SaleableNegotiatedFacade {

    private NegotiatedRepository repository;
    private NegotiatedValidate validate;


    @Autowired
    public NegotiatedServiceImpl(NegotiatedRepository repository, NegotiatedValidate validate) {
        this.repository = repository;
        this.validate = validate;
    }

    @Override
    public Optional<Negotiated> register(NegotiatedInNegotiation negotiatedIn) {
        Negotiated negotiated = negotiatedIn.getNegotiated();
        Negotiation negotiation = negotiatedIn.getNegotiation();
        Saleable saleable = negotiatedIn.getSaleable();
        negotiated.setNegotiation(negotiation);

        validate.checkRules(negotiated, saleable);
        GenerateSaleableItems saleableItems = generateSaleableItems(saleable);

        return seller().save(negotiated).andGenerate(saleableItems).to(negotiation);
    }

    @Override
    public Negotiated update(Negotiated negotiated) {
        validate.checkRules(negotiated, ignoreRules(ruleSaleable(), rulePackage()));

        return seller().update(negotiated);
    }

    @Override
    public Collection<Negotiated> findAll(Negotiation negotiation) {
        return repository.findAll(negotiation);
    }


    @Override
    public BaseRepository<Negotiated, Long> getRepository() {
        return repository;
    }

}
