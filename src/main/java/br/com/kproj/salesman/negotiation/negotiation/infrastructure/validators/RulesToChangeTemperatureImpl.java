package br.com.kproj.salesman.negotiation.negotiation.infrastructure.validators;

import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.negotiation.negotiation.domain.model.approval.ApprovalProcessRepository;
import br.com.kproj.salesman.negotiation.negotiation.domain.model.negotiation.*;
import br.com.kproj.salesman.negotiation.negotiation.domain.model.seller.Seller;
import br.com.kproj.salesman.negotiation.negotiation.domain.model.seller.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static br.com.kproj.salesman.infrastructure.helpers.RuleExpressionHelper.description;

@Component("rulesToChangeTemperature")
public class RulesToChangeTemperatureImpl implements RulesToChangeTemperature {

    @Autowired
    private ApprovalProcessRepository repository;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private NegotiationRepository negotiationRepository;


    private Map<String, CheckChangeTemperatureRule> persistRules = new HashMap<>();

    {
        persistRules.put(description("negotiationold.in.approval.process"),
                (seller, negotiation) -> !repository.isInApprovalProcess(negotiation.getNegotiation()));

        persistRules.put(description("seller.not.found"),
                (seller, negotiation) -> !sellerRepository.findOne(seller.getId()).isPresent());

        persistRules.put(description("negotiationold.not.found"),
                (seller, negotiation) -> !negotiationRepository.findOne(negotiation.getNegotiation().getId()).isPresent());

        persistRules.put(description("negotiationold.already.closedwon"),
                (seller, negotiation) -> {
                    Negotiation negotiationLoaded = negotiationRepository.findOne(negotiation.getNegotiation().getId()).get();
                    return Temperature.CLOSED_WON.equals(negotiationLoaded.getTemperature());
                });
    }

    @Override
    public Boolean isValidBusinessRulesFor(Seller seller, NegotiationChangeTemperature negotiation) {

        Set<String> violations = persistRules.entrySet()
                .stream()
                .filter(e -> e.getValue().check(seller, negotiation))
                .map(Map.Entry::getKey).collect(Collectors.toSet());

        hasErrors(violations).throwing(ValidationException.class);

        return Boolean.TRUE;
    }

    private interface CheckChangeTemperatureRule {

        Boolean check(Seller seller, NegotiationChangeTemperature negotiation);
    }
}
