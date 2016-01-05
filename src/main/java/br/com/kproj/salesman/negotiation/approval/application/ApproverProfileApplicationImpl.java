package br.com.kproj.salesman.negotiation.approval.application;

import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.builders.ApproverProfileBuilder;
import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.ApproverProfile;
import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.RequestApproval;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.helpers.HandlerErrors;
import br.com.kproj.salesman.infrastructure.repository.ApproverProfileRepository;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.repository.RequestApprovalRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;

@Service
public class ApproverProfileApplicationImpl extends BaseModelServiceImpl<ApproverProfile> implements ApproverProfileApplication {

    @Autowired
    private ApproverProfileRepository repository;


    @Override
    public ApproverProfile register(ApproverProfile approverProfile) {
        User approver = approverProfile.getApprover();

        if (approver == null || approver.isNew()) {
            hasErrors(Sets.newHashSet("approver.profile.invalid.user")).throwing(ValidationException.class);
        }

        Optional<ApproverProfile> result = repository.findByApprover(approver);

        if (!result.isPresent()) {
            return super.save(approverProfile);
        }

        return result.get();
    }

    @Override
    public BaseRepository<ApproverProfile, Long> getRepository() {
        return repository;
    }
}
