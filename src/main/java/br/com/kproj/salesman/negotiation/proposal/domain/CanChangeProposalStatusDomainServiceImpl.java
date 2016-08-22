package br.com.kproj.salesman.negotiation.proposal.domain;


import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.RequestApproval;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.repository.*;
import br.com.kproj.salesman.infrastructure.validators.CheckParamsRule;
import br.com.kproj.salesman.negotiation.proposal.approval.application.RequestApprovalApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static br.com.kproj.salesman.infrastructure.helpers.RuleExpressionHelper.description;

@Service
public class CanChangeProposalStatusDomainServiceImpl implements CanChangeProposalStatusDomainService {

    @Autowired
    private ApproverProfileRepository profileRepository;

    @Autowired
    private RequestApprovalApplication requestapproval;


    Map<String, CheckParamsRule<BusinessProposal, UserEntity>> persistRules = new HashMap<>();
    {
        persistRules.put(description("proposal.change.temperature.to.done.with.approvers.without.aproval"),
                (bp, user) -> !profileRepository.hasApproversExcludeParam(user) ? Boolean.FALSE :
                            !requestapproval.findLastRequestApproval(bp).isPresent()
                            || !requestapproval.findLastRequestApproval(bp).get().getStatus().equals(RequestApproval.RequestApprovalStatus.APPROVED)
                );

    }


    @Override
    public Boolean isValidBusinessRulesFor(BusinessProposal businessProposal, Identifiable user) {

        Set<String> violations = persistRules.entrySet()
                .stream()
                .filter(e -> e.getValue().check(businessProposal, (UserEntity)user))
                .map(Map.Entry::getKey).collect(Collectors.toSet());

        hasErrors(violations).throwing(ValidationException.class);

        return Boolean.TRUE;
    }

}
