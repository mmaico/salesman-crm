package br.com.kproj.salesman.negotiation.domain.proposal.saleable;


import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnit;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.repository.Saleable.SaleableUnitRepository;
import br.com.kproj.salesman.negotiation.domain.proposal.CheckRule;
import br.com.kproj.salesman.negotiation.domain.proposal.saleable.contract.SaleablePersistBusinessRules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static br.com.kproj.salesman.negotiation.infrastructure.helpers.RuleExpressionHelper.description;

@Service
public class SaleablePersistBusinessRulesImpl implements SaleablePersistBusinessRules {

    @Autowired
    private SaleableUnitRepository salebleRepository;

    Map<String, CheckRule<SaleableUnit>> saleableUnitRules = new HashMap<>();
    {
        saleableUnitRules.put(description("proposal.saleable.item.wihtout.id"), (saleableUnit) -> saleableUnit == null || saleableUnit.isNew());
        saleableUnitRules.put(description("proposal.saleable.item.notexist"), (saleableUnit) -> !salebleRepository.exists(saleableUnit.getId()));
    }

    public Boolean verifyRules(SaleableUnit saleableUnit) {

        Set<String> violations = saleableUnitRules.entrySet()
                .stream()
                .filter(e -> e.getValue().check(saleableUnit))
                .map(Map.Entry::getKey).collect(Collectors.toSet());

        hasErrors(violations).throwing(ValidationException.class);

        return Boolean.TRUE;
    }
}
