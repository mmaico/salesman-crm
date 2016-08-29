package br.com.kproj.salesman.negotiation.proposal.approval.views.helpers;


import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.enums.ApproverStatus;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;
import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.RequestApprovalEntity;
import br.com.kproj.salesman.infrastructure.security.helpers.SecurityHelper;
import br.com.kproj.salesman.negotiation.proposal.approval.application.RequestApprovalApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RequestApprovalHelper {

    @Autowired
    private RequestApprovalApplication application;

    @Autowired
    private SecurityHelper securityHelper;

    public RequestApprovalEntity getRequestApproval(BusinessProposalEntity proposal) {
        Optional<RequestApprovalEntity> result = application.findLastRequestApproval(proposal);

        return result.isPresent() ? result.get() : null;
    }

    public Boolean isPendingApproval(BusinessProposalEntity proposal) {
        UserEntity user = securityHelper.getPrincipal().getUser();
        Optional<RequestApprovalEntity> result = application.findLastRequestApproval(proposal);
        if (result.isPresent()) {
            if (result.get().getStatus().equals(RequestApprovalEntity.RequestApprovalStatus.WAITING)) {
                long count = result.get().getApprovers().stream().filter(item ->
                        item.getApprover().equals(user) && item.getStatus().equals(ApproverStatus.WAITING)).count();
                return count > 0;
            }
        }

        return Boolean.FALSE;
    }
}
