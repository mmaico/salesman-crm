package br.com.kproj.salesman.administration.approval_negotiation.application.impl;

import br.com.kproj.salesman.administration.approval_negotiation.application.RequestApprovalFacade;
import br.com.kproj.salesman.administration.approval_negotiation.domain.model.approval.*;
import br.com.kproj.salesman.administration.approval_negotiation.domain.model.approver.Approver;
import br.com.kproj.salesman.administration.approval_negotiation.domain.model.approver.ApproverRepository;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

import static br.com.kproj.salesman.administration.approval_negotiation.domain.model.requester.Requester.requester;

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
    public Optional<RequestApproval> register(RequestApproval approval) {
        checkRules.isValidToStartRequestApproval(approval);

        Collection<Approver> approvers = approverRepository.getApproversAvailable();

        Optional<RequestApproval> result = requester().request(approval).withAvailable(approvers);

        eventHandler.newRequestApproval(result.get());

        return result;
    }

    @Override
    public void doEvaluation(EvaluationRequest request) {

        evaluationCheckRules.hasValidInfoToEvaluation(request);

        Optional<RequestApproval> approvers = repository.findOne(request.getNegotiation());

        Boolean needsEvaluation = approvers.get().stillNeedsToBeEvaluationBy(request.getApprover());

        if (needsEvaluation) {
            approvers.get().doEvaluation(request.getApprover(), request.getStatus());

            if (approvers.get().evaluationWasCompleted()) {
                eventHandler.evaluationWasCompleted(approvers.get());
            }
        }
    }

    @Override
    public BaseRepository<RequestApproval, Long> getRepository() {
        return repository;
    }

}
