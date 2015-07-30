package br.com.kproj.salesman.negotiation.domain.proposal.payment.impl;


import static br.com.kproj.salesman.infrastructure.helpers.CollectionsHelper.isEmptySafe;
import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static br.com.kproj.salesman.infrastructure.helpers.NumberHelper.isEquals;
import static br.com.kproj.salesman.infrastructure.helpers.NumberHelper.isNumberEqualsZero;
import static br.com.kproj.salesman.infrastructure.helpers.NumberHelper.isNumberGreaterThanZero;
import static br.com.kproj.salesman.negotiation.infrastructure.helpers.RuleExpressionHelper.description;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.negotiation.domain.proposal.CheckRule;
import br.com.kproj.salesman.negotiation.domain.proposal.payment.PaymentItemPersistBusinessRules;

@Service
public class PaymentItemPersistBusinessRulesImpl implements PaymentItemPersistBusinessRules {

    private Map<String, CheckRule<BusinessProposal>> persistRules = new HashMap<>();
    {
        persistRules.put(description("proposal.verify.payment.invalid.total"),
                (bp) -> !isEmptySafe(bp.getPaymentItems()) && isNumberEqualsZero(bp.getTotal()));
        
        persistRules.put(description("proposal.verify.payment.invalid.payment"),
                (bp) -> isEmptySafe(bp.getPaymentItems()) && isNumberGreaterThanZero(bp.getTotal()));
        
        persistRules.put(description("proposal.verify.total.payment.is.diferent.from.total.products"),
                (bp) -> !isEquals(bp.getTotal(), bp.getTotalToPay()));
        
        persistRules.put(description("proposal.verify.payment.has.item.with.value.zero"),
                (bp) -> bp.getPaymentItems() != null && !bp.getPaymentItems().stream()
    			.filter(e -> isNumberEqualsZero(e.getValue())).collect(Collectors.toList()).isEmpty());
    }
    
    public Boolean verifyRules(BusinessProposal businessProposal) {

    	
    	Set<String> violations = persistRules.entrySet()
                .stream()
                .filter(e -> e.getValue().check(businessProposal))
                .map(Map.Entry::getKey).collect(Collectors.toSet());
    	
        hasErrors(violations).throwing(ValidationException.class);        

        return violations.isEmpty();
    }
}
