package br.com.kproj.salesman.register.application.users;

import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.ApproverEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.UserApproverProfileRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceLegacyImpl;
import br.com.kproj.salesman.register.application.contract.UserApplication;
import br.com.kproj.salesman.register.application.contract.UserApproverProfileApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserApproverProfileApplicationImpl extends BaseModelServiceLegacyImpl<ApproverEntity> implements UserApproverProfileApplication {

    @Autowired
    private UserApproverProfileRepository profileRepository;

    @Autowired
    private UserApplication userApplication;


    @Override
    public Optional<ApproverEntity> register(ApproverEntity userProfile) {

        if (userProfile.getApprover() == null || userProfile.getApprover().isNew()) {
            return Optional.empty();
        }

        Optional<UserEntity> userLoaded = userApplication.getOne(userProfile.getApprover().getId());

        ApproverEntity approverEntitySaved = super.save(userProfile);

        if (userLoaded.isPresent()) {
            userLoaded.get().setApproverEntity(approverEntitySaved);
        }

        return Optional.of(approverEntitySaved);
    }



    public BaseRepositoryLegacy<ApproverEntity, Long> getRepository() {
        return profileRepository;
    }

}
