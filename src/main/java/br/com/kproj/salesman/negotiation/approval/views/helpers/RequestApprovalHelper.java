package br.com.kproj.salesman.negotiation.approval.views.helpers;


import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.enums.ApproverStatus;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.RequestApproval;
import br.com.kproj.salesman.infrastructure.security.helpers.SecurityHelper;
import br.com.kproj.salesman.negotiation.approval.application.RequestApprovalApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RequestApprovalHelper {

    @Autowired
    private RequestApprovalApplication application;

    @Autowired
    private SecurityHelper securityHelper;

    public RequestApproval getRequestApproval(BusinessProposal proposal) {
        Optional<RequestApproval> result = application.findLastRequestApproval(proposal);

        return result.isPresent() ? result.get() : null;
    }

    public Boolean isPendingApproval(BusinessProposal proposal) {
        User user = securityHelper.getPrincipal().getUser();
        Optional<RequestApproval> result = application.findLastRequestApproval(proposal);
        if (result.isPresent()) {
            if (result.get().getStatus().equals(RequestApproval.RequestApprovalStatus.WAITING)) {
                long count = result.get().getApprovers().stream().filter(item ->
                        item.getApprover().equals(user) && item.getStatus().equals(ApproverStatus.WAITING)).count();
                return count > 0;
            }
        }

        return Boolean.FALSE;
    }
}
