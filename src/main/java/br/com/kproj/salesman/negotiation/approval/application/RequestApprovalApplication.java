package br.com.kproj.salesman.negotiation.approval.application;

import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.RequestApproval;
import br.com.kproj.salesman.infrastructure.service.ModelService;

public interface RequestApprovalApplication extends ModelService<RequestApproval> {


    RequestApproval register(RequestApproval requestApproval);

}
