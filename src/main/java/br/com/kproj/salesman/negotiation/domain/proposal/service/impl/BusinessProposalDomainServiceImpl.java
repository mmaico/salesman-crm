package br.com.kproj.salesman.negotiation.domain.proposal.service.impl;


import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.repository.PersonRepository;
import br.com.kproj.salesman.infrastructure.repository.UserRepository;
import br.com.kproj.salesman.negotiation.domain.proposal.CheckRule;
import br.com.kproj.salesman.negotiation.domain.proposal.service.BusinessProposalDomainService;
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

    Map<String, CheckRule<BusinessProposal>> rules = new HashMap<>();

    {
        rules.put(description("proposal.verify.valid.client"), (BusinessProposal bp) -> !bp.getPerson().isNew()
                                                            && clientReposiory.exists(bp.getPerson().getId()));
        rules.put(description("proposal.verify.valid.vendor"), (BusinessProposal bp) -> !bp.getVendor().isNew()
                                                            && userRepository.exists(bp.getVendor().getId()));
    }


    @Override
    public void checkBusinessRulesFor(BusinessProposal businessProposal) {

        Set<String> violations = rules.entrySet()
                .stream()
                .filter(e -> e.getValue().check(businessProposal))
                .map(Map.Entry::getKey).collect(Collectors.toSet());

        hasErrors(violations).throwing(ValidationException.class);


    }
}
