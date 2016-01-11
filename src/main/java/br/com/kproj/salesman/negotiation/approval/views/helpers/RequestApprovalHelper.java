package br.com.kproj.salesman.negotiation.approval.views.helpers;


import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.RequestApproval;
import br.com.kproj.salesman.negotiation.approval.application.RequestApprovalApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RequestApprovalHelper {

    @Autowired
    private RequestApprovalApplication application;

    public RequestApproval getRequestApproval(BusinessProposal proposal) {
        Optional<RequestApproval> result = application.findLastRequestApproval(proposal);

        return result.isPresent() ? result.get() : null;
    }
}
