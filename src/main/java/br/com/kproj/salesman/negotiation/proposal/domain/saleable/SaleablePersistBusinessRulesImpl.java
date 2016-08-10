package br.com.kproj.salesman.negotiation.proposal.domain.saleable;


import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.repository.Saleable.SaleableUnitRepository;
import br.com.kproj.salesman.infrastructure.validators.CheckRule;
import br.com.kproj.salesman.negotiation.proposal.domain.saleable.contract.SaleablePersistBusinessRules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static br.com.kproj.salesman.infrastructure.helpers.RuleExpressionHelper.description;

@Service
public class SaleablePersistBusinessRulesImpl implements SaleablePersistBusinessRules {

    @Autowired
    private SaleableUnitRepository salebleRepository;

    Map<String, CheckRule<SaleableUnitEntity>> saleableUnitRules = new HashMap<>();
    {
        saleableUnitRules.put(description("domain.saleable.item.wihtout.id"), (saleableUnit) -> saleableUnit == null || saleableUnit.isNew());
        saleableUnitRules.put(description("domain.saleable.item.notexist"), (saleableUnit) -> !salebleRepository.exists(saleableUnit.getId()));
    }

    public Boolean verifyRules(SaleableUnitEntity saleableUnit) {

        Set<String> violations = saleableUnitRules.entrySet()
                .stream()
                .filter(e -> e.getValue().check(saleableUnit))
                .map(Map.Entry::getKey).collect(Collectors.toSet());

        hasErrors(violations).throwing(ValidationException.class);

        return Boolean.TRUE;
    }
}
