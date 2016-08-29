package br.com.kproj.salesman.negotiationold.proposal.approval.application;

import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.enums.ApproverStatus;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;
import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.RequestApprovalEntity;
import br.com.kproj.salesman.infrastructure.service.ModelLegacyService;

import java.util.Optional;

public interface RequestApprovalApplication extends ModelLegacyService<RequestApprovalEntity> {


    Optional<RequestApprovalEntity> register(RequestApprovalEntity requestApprovalEntity);

    void evaluationApprover(BusinessProposalEntity proposal, UserEntity user, ApproverStatus status);

    Optional<RequestApprovalEntity> hasRequestApprovalWaitingfor(BusinessProposalEntity proposal);

    Optional<RequestApprovalEntity> findLastRequestApproval(BusinessProposalEntity proposal);

}
