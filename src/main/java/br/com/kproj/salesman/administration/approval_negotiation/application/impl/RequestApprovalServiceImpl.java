package br.com.kproj.salesman.administration.approval_negotiation.application.impl;

import br.com.kproj.salesman.administration.approval_negotiation.application.RequestApprovalFacade;
import br.com.kproj.salesman.administration.approval_negotiation.domain.model.approval.*;
import br.com.kproj.salesman.administration.approval_negotiation.domain.model.approver.Approver;
import br.com.kproj.salesman.administration.approval_negotiation.domain.model.approver.ApproverRepository;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class RequestApprovalServiceImpl extends BaseModelServiceImpl<RequestApproval> implements RequestApprovalFacade {

    @Autowired
    private RequestApprovalRepository repository;

    @Autowired
    private ApproverRepository approverRepository;

    @Autowired
    private RequestApprovalEventHandler eventHandler;

    @Autowired
    private RequestApprovalValidator checkRules;

    @Autowired
    private EvaluationRequestValidator evaluationCheckRules;



    @Override
    public Optional<RequestApproval> register(RequestApproval request) {
        checkRules.isValidToStartRequestApproval(request);

        Collection<Approver> approversAvailable = approverRepository.getApproversAvailable();

        request.startWithApprovers(approversAvailable);

        eventHandler.newRequestApproval(request);
        return repository.save(request);
    }

    @Override
    public void makeEvaluation(EvaluationRequest request) {

        evaluationCheckRules.hasValidInfoToEvaluation(request);

        Optional<RequestApproval> poolApprovers = repository.findOne(request.getNegotiation());

        Boolean needsEvaluation = poolApprovers.get().stillNeedsToBeEvaluationBy(request.getApprover());

        if (needsEvaluation) {
            poolApprovers.get().makeEvaluation(request.getApprover(), request.getStatus());

            if (poolApprovers.get().evaluationWasCompleted()) {
                eventHandler.evaluationWasCompleted(poolApprovers.get());
            }
        }
    }

    @Override
    public BaseRepository<RequestApproval, Long> getRepository() {
        return repository;
    }

}
