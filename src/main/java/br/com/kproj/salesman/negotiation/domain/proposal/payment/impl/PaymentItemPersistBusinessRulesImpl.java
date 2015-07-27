package br.com.kproj.salesman.negotiation.domain.proposal.payment.impl;


import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.negotiation.domain.proposal.CheckRule;
import br.com.kproj.salesman.negotiation.domain.proposal.payment.PaymentItemPersistBusinessRules;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static br.com.kproj.salesman.infrastructure.helpers.CollectionsHelper.isEmptySafe;
import static br.com.kproj.salesman.negotiation.infrastructure.helpers.RuleExpressionHelper.description;

@Service
public class PaymentItemPersistBusinessRulesImpl implements PaymentItemPersistBusinessRules {

    private Map<String, CheckRule<BusinessProposal>> persistRules = new HashMap<>();
    {
        persistRules.put(description("proposal.verify.payment.is.free"),
                (bp) -> isEmptySafe(bp.getPaymentItems()) || BigDecimal.ZERO.compareTo(bp.getTotal()) > 0);

    }

    public Boolean verifyRules(BusinessProposal businessProposal) {

        /**
         *  1 - Se payment nao for vazio e o total for igual a zero (erro)
         *  2 - Se to total for igual a zero e payment nao for vazio (erro)
         *  3 - Se o total de payment for diferente do total da dos produtos(erro)
         *  4 - Se existir payment item com valor zerado (erro)
         */

        return Boolean.FALSE;
    }
}
