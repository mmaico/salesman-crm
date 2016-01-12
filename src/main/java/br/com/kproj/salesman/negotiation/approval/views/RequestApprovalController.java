package br.com.kproj.salesman.negotiation.approval.views;

import br.com.kproj.salesman.infrastructure.entity.builders.BusinessProposalBuilder;
import br.com.kproj.salesman.infrastructure.entity.builders.RequestApprovalBuilder;
import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.RequestApproval;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnit;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.helpers.NormalizeEntityRequest;
import br.com.kproj.salesman.infrastructure.repository.Pager;
import br.com.kproj.salesman.infrastructure.security.authentication.LoggedUser;
import br.com.kproj.salesman.infrastructure.security.helpers.SecurityHelper;
import br.com.kproj.salesman.negotiation.application.NegotiationApplication;
import br.com.kproj.salesman.negotiation.approval.application.RequestApprovalApplication;
import br.com.kproj.salesman.negotiation.infrastructure.validators.BusinessProposalValidator;
import br.com.kproj.salesman.negotiation.view.dto.BusinessProposalRequestMergeHelper;
import br.com.kproj.salesman.negotiation.view.dto.TemperatureDTO;
import br.com.kproj.salesman.negotiation.view.dto.session.ProposalSaleablesDTO;
import br.com.kproj.salesman.register.application.contract.ClientApplication;
import br.com.kproj.salesman.register.application.contract.saleable.SaleableApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

import static br.com.kproj.salesman.infrastructure.entity.builders.BusinessProposalBuilder.createBusinessProposal;

@RestController
public class RequestApprovalController {


    @Autowired
    private RequestApprovalApplication application;

    @Autowired
    private SecurityHelper security;



    @RequestMapping(value = "/request-approval/business-proposal/{proposalId}", method = RequestMethod.POST)
    public @ResponseBody void create(@PathVariable Long proposalId) {

        RequestApproval requestApproval = RequestApprovalBuilder.createRequestApproval()
                .withProposal(BusinessProposalBuilder.createBusinessProposal(proposalId).build())
                .withStatus(RequestApproval.RequestApprovalStatus.WAITING)
                .withUserRequester(security.getPrincipal().getUser()).build();

        application.register(requestApproval);
    }



}
