package br.com.kproj.salesman.negotiationold.proposal.approval.application;

import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.ApproverProfile;
import br.com.kproj.salesman.infrastructure.service.ModelLegacyService;

public interface ApproverProfileApplication extends ModelLegacyService<ApproverProfile> {


    ApproverProfile register(ApproverProfile approverProfile);

}
