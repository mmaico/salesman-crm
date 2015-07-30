package br.com.kproj.salesman.negotiation.domain.proposal;


import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.repository.PersonRepository;
import br.com.kproj.salesman.infrastructure.repository.UserRepository;
import br.com.kproj.salesman.negotiation.domain.proposal.payment.PaymentItemPersistBusinessRules;
import br.com.kproj.salesman.negotiation.domain.proposal.product.ProductItemPersistBusinessRules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static br.com.kproj.salesman.negotiation.infrastructure.helpers.RuleExpressionHelper.description;

@Service
public class BusinessProposalDomainServiceImpl implements BusinessProposalDomainService {

    @Autowired
    private PersonRepository clientReposiory;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductItemPersistBusinessRules productIService;

    @Autowired
    private PaymentItemPersistBusinessRules paymentService;

    Map<String, CheckRule<BusinessProposal>> persistRules = new HashMap<>();
    {
        persistRules.put(description("proposal.verify.valid.client"), (bp) -> !(!(bp).getPerson().isNew() && clientReposiory.exists(bp.getPerson().getId())));
        persistRules.put(description("proposal.verify.valid.vendor"), (bp) -> !(!(bp).getVendor().isNew() && userRepository.exists(bp.getVendor().getId())));
        persistRules.put(description("proposal.verify.valid.product.items"), (bp) -> !productIService.verifyRules(bp));
        persistRules.put(description("proposal.verify.valid.payment.items"), (bp) -> !paymentService.verifyRules(bp));
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
