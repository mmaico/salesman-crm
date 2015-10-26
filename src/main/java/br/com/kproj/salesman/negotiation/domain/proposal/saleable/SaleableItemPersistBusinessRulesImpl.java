package br.com.kproj.salesman.negotiation.domain.proposal.saleable;


import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.entity.proposal.ProposalSaleableItem;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.negotiation.domain.proposal.CheckRule;
import br.com.kproj.salesman.negotiation.domain.proposal.saleable.contract.SaleableItemPersistBusinessRules;
import br.com.kproj.salesman.negotiation.domain.proposal.saleable.contract.SaleablePersistBusinessRules;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static br.com.kproj.salesman.infrastructure.helpers.NumberHelper.isNotNegativeNumber;
import static br.com.kproj.salesman.negotiation.infrastructure.helpers.RuleExpressionHelper.description;

@Service
public class SaleableItemPersistBusinessRulesImpl implements SaleableItemPersistBusinessRules {


    @Autowired
    private SaleablePersistBusinessRules saleablePersistBusinessRules;

    Map<String, CheckRule<ProposalSaleableItem>> saleableUnitRules = new HashMap<>();
    {
        saleableUnitRules.put(description("proposal.item.wihtout.price"), (proposalSaleableItem) -> !isNotNegativeNumber(proposalSaleableItem.getPrice()));
        saleableUnitRules.put(description("proposal.item.without.quantity"), (proposalSaleableItem) -> proposalSaleableItem.getQuantity() > 0);
    }

    public Boolean verifyRules(BusinessProposal businessProposal) {
        Set<String> violations = Sets.newHashSet();

        for(ProposalSaleableItem item: businessProposal.getSaleableItems()) {
            violations = saleableUnitRules.entrySet()
                    .stream()
                    .filter(e -> !saleablePersistBusinessRules.verifyRules(item.getSaleableUnit())
                                || !isNotNegativeNumber(item.getPrice())
                                || !(item.getQuantity() > 0))
                    .map(Map.Entry::getKey).collect(Collectors.toSet());

            hasErrors(violations).throwing(ValidationException.class);
        }

        return Boolean.TRUE;
    }
}
