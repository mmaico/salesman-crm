package br.com.kproj.salesman.administration.approval_negotiation.application.impl;

import br.com.kproj.salesman.administration.approval_negotiation.application.RequestApprovalFacade;
import br.com.kproj.salesman.administration.approval_negotiation.domain.model.approval.RequestApproval;
import br.com.kproj.salesman.administration.approval_negotiation.domain.model.approval.RequestApprovalEventHandler;
import br.com.kproj.salesman.administration.approval_negotiation.domain.model.approval.RequestApprovalRepository;
import br.com.kproj.salesman.administration.approval_negotiation.domain.model.approval.RequestApprovalValidator;
import br.com.kproj.salesman.administration.approval_negotiation.domain.model.approver.Approver;
import br.com.kproj.salesman.administration.approval_negotiation.domain.model.approver.ApproverRepository;
import br.com.kproj.salesman.administration.approval_negotiation.domain.model.negotiation.Negotiation;
import br.com.kproj.salesman.infrastructure.entity.enums.ApproverStatus;
import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.RequestApprovalEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.Product;
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
    @Qualifier("requestApprovalBusiness")
    private RequestApprovalValidator checkRules;


    @Override
    public Optional<RequestApproval> register(RequestApproval request) {
        checkRules.isValidToStartRequestApproval(request);

        Collection<Approver> approversAvailable = approverRepository.getApproversAvailable();

        request.startWithApprovers(approversAvailable);

        eventHandler.newRequestApproval(request);
        return repository.save(request);
    }

    @Override
    public void evaluationApprover(Negotiation negotiation, Approver user, ApproverStatus status) {
        
//        Optional<RequestApprovalEntity> requestApprovalLoaded = repository.findByProposal(proposal);
//
//        if (!requestApprovalLoaded.isPresent()) return;
//
//        Optional<Approver> approverFound = requestApprovalLoaded.get().getApprovers().stream()
//                .filter(item -> item.getApprover().equals(user) && item.getStatus().equals(ApproverStatus.WAITING))
//                .findFirst();
//
//        if (approverFound.isPresent()) {
//            approverFound.get().setStatus(status);
//            RequestApprovalEntity requestApprovalEntity = requestApprovalLoaded.get();
//            requestApprovalEntity.setCurrentStatus();
//
//            if (!requestApprovalEntity.getStatus().equals(RequestApprovalEntity.RequestApprovalStatus.WAITING)) {
//                eventBus.post(RequestApprovalFinalizeMessage.create(requestApprovalEntity));
//            }
//        }

    }

    @Override
    public BaseRepository<RequestApproval, Long> getRepository() {
        return repository;
    }

}
