package br.com.kproj.salesman.administration.approval_negotiation.application.impl;

import br.com.kproj.salesman.administration.approval_negotiation.application.ApproverFacade;
import br.com.kproj.salesman.administration.approval_negotiation.domain.model.approver.Approver;
import br.com.kproj.salesman.administration.approval_negotiation.domain.model.approver.ApproverRepository;
import br.com.kproj.salesman.administration.approval_negotiation.domain.model.approver.ApproverValidator;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static br.com.kproj.salesman.administration.approval_negotiation.domain.model.user.User.user;

@Service
public class ApproverServiceImpl extends BaseModelServiceImpl<Approver> implements ApproverFacade {

    private ApproverRepository repository;

    private ApproverValidator validator;

    @Autowired
    public ApproverServiceImpl(ApproverRepository repository, ApproverValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }


    @Override
    public Approver makeAvailable(Approver approver) {
        validator.isValid(approver);
        return user().makeAvailable(approver);
    }

    @Override
    public Approver makeAnavailable(Approver approver) {
        validator.exists(approver);
        return user().makeUnavailable(approver);
    }

    @Override
    public Collection<Approver> getApprovers() {
        return repository.getApproversAvailable();
    }

    @Override
    public BaseRepository<Approver, Long> getRepository() {
        return repository;
    }

}
