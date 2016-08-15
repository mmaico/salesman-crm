package br.com.kproj.salesman.negotiation.proposal.approval.application;

import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.ApproverProfile;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.repository.ApproverProfileRepository;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.UserRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceLegacyImpl;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;

@Service
public class ApproverProfileApplicationImpl extends BaseModelServiceLegacyImpl<ApproverProfile> implements ApproverProfileApplication {

    @Autowired
    private ApproverProfileRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ApproverProfile register(ApproverProfile approverProfile) {
        User approver = approverProfile.getApprover();

        if (approver == null || approver.isNew()) {
            hasErrors(Sets.newHashSet("approver.profile.invalid.user")).throwing(ValidationException.class);
        }

        Optional<ApproverProfile> result = repository.findByApprover(approver);

        if (!result.isPresent()) {
            ApproverProfile profileSaved = super.save(approverProfile);
            User userLoaded = userRepository.findOne(approver.getId());
            userLoaded.setApproverProfile(profileSaved);

            return profileSaved;
        }

        return result.get();
    }

    public BaseRepositoryLegacy<ApproverProfile, Long> getRepository() {
        return repository;
    }
}
