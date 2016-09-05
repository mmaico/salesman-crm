package br.com.kproj.salesman.administration.approval_negotiation.application.predicates;


import br.com.kproj.salesman.infrastructure.events.messages.ProposalAuditingAfterUpdateMessage;
import br.com.kproj.salesman.infrastructure.repository.ApproverProfileRepository;
import br.com.kproj.salesman.infrastructure.repository.RequestApprovalEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

import static br.com.kproj.salesman.infrastructure.entity.builders.BusinessProposalBuilder.createBusinessProposal;

@Component
public class StartApprovalProcessPredicate implements Predicate<ProposalAuditingAfterUpdateMessage> {

    @Autowired
    private ApprovalHadChangesPredicate hadChangesPredicate;

    @Autowired
    private RequestApprovalEntityRepository repository;

    @Autowired
    private ApproverProfileRepository profileRepository;



    @Override
    public boolean test(ProposalAuditingAfterUpdateMessage auditing) {
        Boolean hasRequestApprovalApprovedToProposal = repository.hasRequestApprovalApproved(createBusinessProposal(auditing.getBefore().getEntityId()).build());
        Boolean hasApprovers = profileRepository.hasApproversExcludeParam(auditing.getUserThatChanged());
        Boolean hadChanges = hadChangesPredicate.test(auditing);

        return hasRequestApprovalApprovedToProposal && hasApprovers && hadChanges;
    }
}
