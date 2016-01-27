package br.com.kproj.salesman.register.application.users;

import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.ApproverProfile;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.repository.UserApproverProfileRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import br.com.kproj.salesman.register.application.contract.UserApplication;
import br.com.kproj.salesman.register.application.contract.UserApproverProfileApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserApproverProfileApplicationImpl extends BaseModelServiceImpl<ApproverProfile> implements UserApproverProfileApplication {

    @Autowired
    private UserApproverProfileRepository profileRepository;

    @Autowired
    private UserApplication userApplication;


    @Override
    public Optional<ApproverProfile> register(ApproverProfile userProfile) {

        if (userProfile.getApprover() == null || userProfile.getApprover().isNew()) {
            return Optional.empty();
        }

        Optional<User> userLoaded = userApplication.getOne(userProfile.getApprover().getId());

        ApproverProfile approverProfileSaved = super.save(userProfile);

        if (userLoaded.isPresent()) {
            userLoaded.get().setApproverProfile(approverProfileSaved);
        }

        return Optional.of(approverProfileSaved);
    }



    public BaseRepository<ApproverProfile, Long> getRepository() {
        return profileRepository;
    }

}
