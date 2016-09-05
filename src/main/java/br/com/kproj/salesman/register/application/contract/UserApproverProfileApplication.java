package br.com.kproj.salesman.register.application.contract;

import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.ApproverEntity;
import br.com.kproj.salesman.infrastructure.service.ModelLegacyService;

import java.util.Optional;


public interface UserApproverProfileApplication extends ModelLegacyService<ApproverEntity> {

    Optional<ApproverEntity> register(ApproverEntity userProfile);

}
