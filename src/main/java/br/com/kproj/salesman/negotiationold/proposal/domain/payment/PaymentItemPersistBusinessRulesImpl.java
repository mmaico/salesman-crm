package br.com.kproj.salesman.negotiationold.proposal.domain.payment;


import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.validators.CheckRuleLegacy;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.helpers.CollectionsHelper.isEmptySafe;
import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static br.com.kproj.salesman.infrastructure.helpers.NumberHelper.*;
import static br.com.kproj.salesman.infrastructure.helpers.RuleExpressionHelper.description;

@Service
public class PaymentItemPersistBusinessRulesImpl implements PaymentItemPersistBusinessRules {

    private Map<String, CheckRuleLegacy<BusinessProposalEntity>> persistRules = new HashMap<>();
    {
        persistRules.put(description("proposal.has.payment.with.sale.with.total.zero"),
                (bp) -> !isEmptySafe(bp.getPaymentItems()) && isNumberEqualsZero(bp.getTotal()));
        
        persistRules.put(description("proposal.no.payment.to.sale.with.total.greater.zero"),
                (bp) -> isEmptySafe(bp.getPaymentItems()) && isNumberGreaterThanZero(bp.getTotal()));
        
        persistRules.put(description("proposal.verify.total.payment.is.diferent.from.total.products"),
                (bp) -> !isEquals(bp.getTotal(), bp.getTotalToPay()));
        
        persistRules.put(description("proposal.verify.payment.has.item.with.value.less.than.zero"),
                (bp) -> bp.getPaymentItems() != null && !bp.getPaymentItems().stream()
    			.filter(e -> isNegativeNumber(e.getValue())).collect(Collectors.toList()).isEmpty());

        persistRules.put(description("proposal.verify.payment.has.invalid.due.date"),
                     (bp) -> bp.getPaymentItems() == null ||!bp.getPaymentItems().stream()
                            .filter(e -> e.getDueDate() == null || e.getDueDate().before(new Date()))
                            .collect(Collectors.toList()).isEmpty()
                        );
    }
    
    public Boolean verifyRules(BusinessProposalEntity businessProposalEntity) {

    	
    	Set<String> violations = persistRules.entrySet()
                .stream()
                .filter(e -> e.getValue().check(businessProposalEntity))
                .map(Map.Entry::getKey).collect(Collectors.toSet());
    	
        hasErrors(violations).throwing(ValidationException.class);        

        return violations.isEmpty();
    }
}
