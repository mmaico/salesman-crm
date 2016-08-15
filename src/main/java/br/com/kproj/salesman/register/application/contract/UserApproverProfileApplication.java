package br.com.kproj.salesman.register.application.contract;

import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.ApproverProfile;
import br.com.kproj.salesman.infrastructure.service.ModelLegacyService;

import java.util.Optional;


public interface UserApproverProfileApplication extends ModelLegacyService<ApproverProfile> {

    Optional<ApproverProfile> register(ApproverProfile userProfile);

}
