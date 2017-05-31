package br.com.kproj.salesman.administration.approval_negotiation.view;

import br.com.kproj.salesman.administration.approval_negotiation.application.ApproverFacade;
import br.com.kproj.salesman.administration.approval_negotiation.domain.model.approver.Approver;
import br.com.kproj.salesman.administration.approval_negotiation.domain.model.user.User;
import br.com.kproj.salesman.administration.approval_negotiation.view.support.builders.ApproverResourceBuilder;
import br.com.kproj.salesman.administration.approval_negotiation.view.support.resources.ApproverResource;
import br.com.kproj.salesman.infrastructure.exceptions.NotFoundException;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
public class ApproverEndpoint {


    private ApproverFacade service;

    private ApproverResourceBuilder builder;



    @Autowired
    public ApproverEndpoint(ApproverFacade service, ApproverResourceBuilder builder) {
        this.service = service;
        this.builder = builder;
    }


    @RequestMapping(value = "/rs/approvers", method = RequestMethod.GET)
    public @ResponseBody ResourceItems list() {

        Collection<Approver> approvers = service.getApprovers();

        return builder.build(approvers);
    }

    @RequestMapping(value = "/rs/approvers/{approverId}", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItem getOne(@PathVariable Long approverId) {

        Optional<Approver> approverOptional = service.getOne(approverId);
        Approver approverFound = approverOptional.orElseThrow(NotFoundException::new);

        return builder.build(approverFound);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/rs/approvers", method = RequestMethod.POST)
    public @ResponseBody
    ResourceItem create(@RequestBody ApproverResource resource) {

        Approver approver = new Approver();
        if (resource.getUserId() != null) {
            approver.setUser(new User(resource.getUserId()));
        }

        Approver approverSaved = service.makeAnavailable(approver);

        return builder.build(approverSaved);
    }

    @RequestMapping(value = "/rs/approvers/{approverId}", method = RequestMethod.DELETE)
    public @ResponseBody
    void delete(@PathVariable Long approverId) {

        service.makeAnavailable(new Approver(approverId));
    }


    
}
