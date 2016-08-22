package br.com.kproj.salesman.negotiation.proposal.domain;


import br.com.kproj.salesman.infrastructure.entity.enums.ProposalTemperature;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.repository.PersonRepository;
import br.com.kproj.salesman.infrastructure.repository.UserEntityRepository;
import br.com.kproj.salesman.infrastructure.validators.CheckRuleLegacy;
import br.com.kproj.salesman.negotiation.proposal.domain.payment.PaymentItemPersistBusinessRules;
import br.com.kproj.salesman.negotiation.proposal.domain.saleable.contract.SaleableItemPersistBusinessRules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.helpers.CollectionsHelper.isEmptySafe;
import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static br.com.kproj.salesman.infrastructure.helpers.RuleExpressionHelper.description;

@Service
public class BusinessProposalDomainServiceImpl implements BusinessProposalDomainService {

    @Autowired
    private PersonRepository clientReposiory;

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Autowired
    private SaleableItemPersistBusinessRules productIService;

    @Autowired
    private PaymentItemPersistBusinessRules paymentService;

    Map<String, CheckRuleLegacy<BusinessProposal>> persistRules = new HashMap<>();
    {

        persistRules.put(description("proposal.verify.won.cannotbe.changed"), (bp) -> ProposalTemperature.CLOSED_WON == bp.getTemperature());
        persistRules.put(description("domain.verify.valid.client"), (bp) -> !(!(bp).getClient().isNew() && clientReposiory.exists(bp.getClient().getId())));
        persistRules.put(description("domain.verify.valid.vendor"), (bp) -> !(!(bp).getSeller().isNew() && userEntityRepository.exists(bp.getSeller().getId())));
        persistRules.put(description("proposal.verify.not.empty.products"), (bp) -> isEmptySafe(bp.getSaleableItems()));
        persistRules.put(description("proposal.verify.not.empty.payment"), (bp) -> isEmptySafe(bp.getPaymentItems()));
        persistRules.put(description("domain.verify.valid.product.items"), (bp) -> !productIService.verifyRules(bp));
        persistRules.put(description("domain.verify.valid.payment.items"), (bp) -> !paymentService.verifyRules(bp));
    }


    @Override
    public void checkBusinessRulesFor(BusinessProposal businessProposal) {

        Set<String> violations = persistRules.entrySet()
                .stream()
                .filter(e -> e.getValue().check(businessProposal))
                .map(Map.Entry::getKey).collect(Collectors.toSet());

        hasErrors(violations).throwing(ValidationException.class);
    }

}
