package br.com.kproj.salesman.negotiation.domain.proposal.saleable;


import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.entity.proposal.ProposalSaleableItem;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.negotiation.domain.proposal.CheckRule;
import br.com.kproj.salesman.negotiation.domain.proposal.saleable.contract.PackageBusinessRules;
import br.com.kproj.salesman.negotiation.domain.proposal.saleable.contract.SaleableItemPersistBusinessRules;
import br.com.kproj.salesman.negotiation.domain.proposal.saleable.contract.SaleablePersistBusinessRules;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.helpers.CollectionsHelper.isEmptySafe;
import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static br.com.kproj.salesman.infrastructure.helpers.NumberHelper.isNotNegativeNumber;
import static br.com.kproj.salesman.infrastructure.helpers.RuleExpressionHelper.description;

@Service
public class SaleableItemPersistBusinessRulesImpl implements SaleableItemPersistBusinessRules {


    @Autowired
    private SaleablePersistBusinessRules saleablePersistBusinessRules;

    @Autowired
    private PackageBusinessRules packageBusinessRules;

    Map<String, CheckRule<ProposalSaleableItem>> saleableUnitRules = new HashMap<>();
    {
        saleableUnitRules.put(description("proposal.item.wihtout.price"), (proposalSaleableItem) -> isNotNegativeNumber(proposalSaleableItem.getPrice()));
        saleableUnitRules.put(description("proposal.item.without.quantity"), (proposalSaleableItem) -> proposalSaleableItem.getQuantity() > 0);
        saleableUnitRules.put(description("proposal.item.invalid.original.price"), (proposalSaleableItem) -> proposalSaleableItem.getOriginalPrice() != null);
    }

    public Boolean verifyRules(BusinessProposal businessProposal) {
        Set<String> violations = Sets.newHashSet();

        if (isEmptySafe(businessProposal.getSaleableItems())) {
            throw new ValidationException(Sets.newHashSet("proposal.must.be.products"));
        }

        for(ProposalSaleableItem item: businessProposal.getSaleableItems()) {
            violations = saleableUnitRules.entrySet()
                    .stream()
                    .filter(e -> !saleablePersistBusinessRules.verifyRules(item.hasPackage() ? item.getSalePackage() : item.getSaleableUnit())
                            || !e.getValue().check(item))
                    .map(Map.Entry::getKey).collect(Collectors.toSet());

            hasErrors(violations).throwing(ValidationException.class);
        }

        packageBusinessRules.verifyRules(businessProposal.getSaleableItems());

        return Boolean.TRUE;
    }
}
