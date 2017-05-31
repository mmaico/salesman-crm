package br.com.kproj.salesman.administration.approval_negotiation.application.validates;


import br.com.kproj.salesman.administration.approval_negotiation.domain.model.approval.EvaluationRequest;
import br.com.kproj.salesman.administration.approval_negotiation.domain.model.approval.EvaluationRequestValidator;
import br.com.kproj.salesman.administration.approval_negotiation.domain.model.approval.RequestApprovalRepository;
import br.com.kproj.salesman.administration.approval_negotiation.domain.model.approver.ApproverRepository;
import br.com.kproj.salesman.administration.approval_negotiation.domain.model.negotiation.NegotiationRepository;
import br.com.kproj.salesman.infrastructure.validators.CheckRule;
import br.com.kproj.salesman.infrastructure.validators.RuleKey;
import br.com.kproj.salesman.infrastructure.validators.RulesExecute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static br.com.kproj.salesman.infrastructure.validators.RuleKey.key;

@Component
public class EvaluationRequestBusinessRules implements EvaluationRequestValidator {

    @Autowired
    private NegotiationRepository negotiationRepository;

    @Autowired
    private ApproverRepository approverRepository;

    @Autowired
    private RequestApprovalRepository repository;

    private Map<RuleKey, CheckRule<EvaluationRequest>> persistRules = new HashMap<>();
    {
        persistRules.put(key("evaluation.has.invalid.negotiation"), (request) ->
            request.getNegotiation() == null
                    && request.getNegotiation().isNew()
                    && !negotiationRepository.findOne(request.getNegotiation().getId()).isPresent()
        );
        persistRules.put(key("evaluation.has.invalid.approver"), (request) ->
            request.getApprover() == null && request.getApprover().isNew()
                && !approverRepository.findOne(request.getApprover().getId()).isPresent()
        );
        persistRules.put(key("evaluation.has.invalid.status"), (request) -> request.getStatus() == null);
        persistRules.put(key("evaluation.not.has.request.approval"), (request) -> !repository.findOne(request.getNegotiation()).isPresent());
    }


    @Override
    public void hasValidInfoToEvaluation(EvaluationRequest request) {
        RulesExecute.runRules(persistRules, request);
    }
}
