package br.com.kproj.salesman.negotiation.approval.application;

import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.ApproverProfile;
import br.com.kproj.salesman.infrastructure.service.ModelService;

public interface ApproverProfileApplication extends ModelService<ApproverProfile> {


    ApproverProfile register(ApproverProfile approverProfile);

}
