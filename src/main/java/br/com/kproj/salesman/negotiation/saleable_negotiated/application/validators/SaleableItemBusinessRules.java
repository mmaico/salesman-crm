package br.com.kproj.salesman.negotiation.saleable_negotiated.application.validators;

import br.com.kproj.salesman.infrastructure.validators.CheckRule;
import br.com.kproj.salesman.infrastructure.validators.RuleKey;
import br.com.kproj.salesman.infrastructure.validators.RulesExecute;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.negotiated.Negotiated;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.negotiation.NegotiationRepository;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleable.Saleable;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleable.SaleablePackage;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleable.SaleableRepository;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleables_items.SaleableItem;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleables_items.SaleableItemRepository;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleables_items.SaleableItemValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static br.com.kproj.salesman.negotiation.saleable_negotiated.application.validators.SaleableItemRulesDescription.*;


@Component
public class SaleableItemBusinessRules implements SaleableItemValidate {


    private SaleableRepository saleableRepository;

    private NegotiationRepository repository;

    private SaleableItemRepository itemRepository;

    @Autowired
    public SaleableItemBusinessRules(SaleableRepository saleableRepository, NegotiationRepository repository, SaleableItemRepository itemRepository) {
        this.saleableRepository = saleableRepository;
        this.repository = repository;
        this.itemRepository = itemRepository;
    }


    private Map<RuleKey, CheckRule<SaleableItem>> persistRules = new HashMap<>();
    {

        persistRules.put(ruleSaleable(), (saleableItem) -> !saleableRepository.findOne(saleableItem.getSaleable().getId()).isPresent());

        persistRules.put(rulePackage(), (saleableItem) -> {
            if (saleableItem.getUsedPackage() == null) return Boolean.FALSE;

            Optional<Saleable> saleableFound = saleableRepository.findOne(saleableItem.getUsedPackage().getId());
            return !(saleableFound.get() instanceof SaleablePackage);
        });

        persistRules.put(ruleSaleableDoesNotBelongPackage(), (saleableItem) -> {
            if (saleableItem.getUsedPackage() == null) return Boolean.FALSE;

            Optional<Saleable> saleableFound = saleableRepository.findOne(saleableItem.getUsedPackage().getId());
            return !((SaleablePackage)saleableFound.get()).getSaleables().contains(saleableItem.getSaleable());
        });

        persistRules.put(ruleNegotiated(), (saleableItem) -> {
            Negotiated negotiated = saleableItem.getNegotiated();
            return negotiated == null || negotiated.isNew() || !repository.findOne(negotiated.getId()).isPresent();
        });

        persistRules.put(ruleSaleableItemExists(), (saleableItem) ->
            itemRepository.alreadyExists(saleableItem.getNegotiated(), saleableItem.getSaleable(), saleableItem.getUsedPackage())
        );

    }

    @Override
    public void checkRules(SaleableItem saleableItem) {

        RulesExecute.runRules(persistRules, saleableItem);
    }

}
