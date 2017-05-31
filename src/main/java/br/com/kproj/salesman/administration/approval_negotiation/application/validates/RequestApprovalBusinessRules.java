package br.com.kproj.salesman.administration.approval_negotiation.application.validates;

import br.com.kproj.salesman.administration.approval_negotiation.domain.model.approval.RequestApproval;
import br.com.kproj.salesman.administration.approval_negotiation.domain.model.approval.RequestApprovalRepository;
import br.com.kproj.salesman.administration.approval_negotiation.domain.model.approval.RequestApprovalValidator;
import br.com.kproj.salesman.administration.approval_negotiation.domain.model.approver.ApproverRepository;
import br.com.kproj.salesman.infrastructure.validators.CheckRule;
import br.com.kproj.salesman.infrastructure.validators.RuleKey;
import br.com.kproj.salesman.infrastructure.validators.RulesExecute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static br.com.kproj.salesman.infrastructure.validators.RuleKey.key;

@Component("requestApprovalBusiness")
public class RequestApprovalBusinessRules implements RequestApprovalValidator {

    @Autowired
    private RequestApprovalRepository repository;

    @Autowired
    private ApproverRepository approverRepository;

    private Map<RuleKey, CheckRule<RequestApproval>> persistRules = new HashMap<>();
    {
        persistRules.put(key("request.has.valid.negotiation"), (request) -> request.getNegotiation() == null && request.getNegotiation().isNew());
        persistRules.put(key("negotiation.is.already.in.approval.process"), (request) -> repository.findOne(request.getNegotiation()).isPresent());
        persistRules.put(key("request.dont.have.requester"), (request) -> request.getRequester() == null && request.getRequester().isNew());
        persistRules.put(key("request.not.have.approval.available"), (request) -> !approverRepository.hasApproversAvailable());
    }

    @Override
    public void isValidToStartRequestApproval(RequestApproval request) {
        RulesExecute.runRules(persistRules, request);
    }
}
