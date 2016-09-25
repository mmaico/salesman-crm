package br.com.kproj.salesman.administration.approval_negotiation.application.validates;

import br.com.kproj.salesman.administration.approval_negotiation.domain.model.approval.RequestApproval;
import br.com.kproj.salesman.administration.approval_negotiation.domain.model.approval.RequestApprovalRepository;
import br.com.kproj.salesman.administration.approval_negotiation.domain.model.approval.RequestApprovalValidator;
import br.com.kproj.salesman.administration.approval_negotiation.domain.model.approver.ApproverRepository;
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

@Component("requestApprovalBusiness")
public class RequestApprovalBusinessRules implements RequestApprovalValidator {

    @Autowired
    private RequestApprovalRepository repository;

    @Autowired
    private ApproverRepository approverRepository;

    private Map<String, CheckRule<RequestApproval>> persistRules = new HashMap<>();
    {
        persistRules.put(description("request.has.valid.negotiation"), (request) -> request.getNegotiation() == null && request.getNegotiation().isNew());
        persistRules.put(description("negotiation.is.already.in.approval.process"), (request) -> repository.findOne(request.getNegotiation()).isPresent());
        persistRules.put(description("request.dont.have.requester"), (request) -> request.getRequester() == null && request.getRequester().isNew());
        persistRules.put(description("request.not.have.approval.available"), (request) -> !approverRepository.hasApproversAvailable());
    }

    @Override
    public void isValidToStartRequestApproval(RequestApproval request) {

        Set<String> errors = persistRules.entrySet()
                .stream()
                .filter(e -> e.getValue().check(request))
                .map(Map.Entry::getKey).collect(Collectors.toSet());

        hasErrors(errors).throwing(ValidationException.class);
    }
}
