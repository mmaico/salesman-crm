package br.com.kproj.salesman.administration.approval_negotiation.application.validates;


import br.com.kproj.salesman.administration.approval_negotiation.domain.model.approval.EvaluationRequest;
import br.com.kproj.salesman.administration.approval_negotiation.domain.model.approval.EvaluationRequestValidator;
import br.com.kproj.salesman.administration.approval_negotiation.domain.model.approval.RequestApprovalRepository;
import br.com.kproj.salesman.administration.approval_negotiation.domain.model.approver.ApproverRepository;
import br.com.kproj.salesman.administration.approval_negotiation.domain.model.negotiation.NegotiationRepository;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.validators.CheckRule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static br.com.kproj.salesman.infrastructure.helpers.RuleExpressionHelper.description;

@Component
public class EvaluationRequestBusinessRules implements EvaluationRequestValidator {

    @Autowired
    private NegotiationRepository negotiationRepository;

    @Autowired
    private ApproverRepository approverRepository;

    @Autowired
    private RequestApprovalRepository repository;

    private Map<String, CheckRule<EvaluationRequest>> persistRules = new HashMap<>();
    {
        persistRules.put(description("evaluation.has.invalid.negotiation"), (request) ->
            request.getNegotiation() == null
                    && request.getNegotiation().isNew()
                    && !negotiationRepository.findOne(request.getNegotiation().getId()).isPresent()
        );
        persistRules.put(description("evaluation.has.invalid.approver"), (request) ->
            request.getApprover() == null && request.getApprover().isNew()
                && !approverRepository.findOne(request.getApprover().getId()).isPresent()
        );
        persistRules.put(description("evaluation.has.invalid.status"), (request) -> request.getStatus() == null);
        persistRules.put(description("evaluation.not.has.request.approval"), (request) -> !repository.findOne(request.getNegotiation()).isPresent());
    }


    @Override
    public void hasValidInfoToEvaluation(EvaluationRequest request) {

        Set<String> errors = persistRules.entrySet()
                .stream()
                .filter(e -> e.getValue().check(request))
                .map(Map.Entry::getKey).collect(Collectors.toSet());

        hasErrors(errors).throwing(ValidationException.class);
    }
}
