package br.com.kproj.salesman.register.application.contract;

import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.ApproverProfile;
import br.com.kproj.salesman.infrastructure.service.ModelService;

import java.util.Optional;


public interface UserApproverProfileApplication extends ModelService<ApproverProfile> {

    Optional<ApproverProfile> register(ApproverProfile userProfile);

}
