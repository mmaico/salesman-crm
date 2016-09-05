package br.com.kproj.salesman.administration.approval_negotiation.view;

import br.com.kproj.salesman.administration.approval_negotiation.application.RequestApprovalFacade;
import br.com.kproj.salesman.infrastructure.entity.builders.BusinessProposalBuilder;
import br.com.kproj.salesman.infrastructure.entity.builders.RequestApprovalBuilder;
import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.RequestApprovalEntity;
import br.com.kproj.salesman.infrastructure.security.helpers.SecurityHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RequestApprovalController {


    @Autowired
    private RequestApprovalFacade application;

    @Autowired
    private SecurityHelper security;



    @RequestMapping(value = "/request-approval/business-proposal/{proposalId}", method = RequestMethod.POST)
    public @ResponseBody void create(@PathVariable Long proposalId) {

        RequestApprovalEntity requestApprovalEntity = RequestApprovalBuilder.createRequestApproval()
                .withProposal(BusinessProposalBuilder.createBusinessProposal(proposalId).build())
                .withStatus(RequestApprovalEntity.RequestApprovalStatus.WAITING).build();
                //.withUserRequester(security.getPrincipal().getUser()).build();

        //application.register(requestApprovalEntity);
    }



}
