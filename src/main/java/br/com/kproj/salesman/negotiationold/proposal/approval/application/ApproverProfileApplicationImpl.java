package br.com.kproj.salesman.negotiationold.proposal.approval.application;

import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.ApproverProfile;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.repository.ApproverProfileRepository;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.UserEntityRepository;
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
    private UserEntityRepository userEntityRepository;

    @Override
    public ApproverProfile register(ApproverProfile approverProfile) {
        UserEntity approver = approverProfile.getApprover();

        if (approver == null || approver.isNew()) {
            hasErrors(Sets.newHashSet("approver.profile.invalid.user")).throwing(ValidationException.class);
        }

        Optional<ApproverProfile> result = repository.findByApprover(approver);

        if (!result.isPresent()) {
            ApproverProfile profileSaved = super.save(approverProfile);
            UserEntity userLoaded = userEntityRepository.findOne(approver.getId());
            userLoaded.setApproverProfile(profileSaved);

            return profileSaved;
        }

        return result.get();
    }

    public BaseRepositoryLegacy<ApproverProfile, Long> getRepository() {
        return repository;
    }
}
