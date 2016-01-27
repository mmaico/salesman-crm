package br.com.kproj.salesman.negotiation.approval.application;

import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.enums.ApproverStatus;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.Approver;
import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.ApproverProfile;
import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.RequestApproval;
import br.com.kproj.salesman.infrastructure.events.messages.RequestApprovalFinalizeMessage;
import br.com.kproj.salesman.infrastructure.events.messages.RequestNewApprovalMessage;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.repository.ApproverProfileRepository;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.repository.Pager;
import br.com.kproj.salesman.infrastructure.repository.RequestApprovalRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import com.google.common.collect.Sets;
import com.google.common.eventbus.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static br.com.kproj.salesman.infrastructure.entity.builders.ApproverBuilder.createApprover;
import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;

@Service
public class RequestApprovalApplicationImpl extends BaseModelServiceImpl<RequestApproval> implements RequestApprovalApplication {

    @Autowired
    private RequestApprovalRepository repository;

    @Autowired
    private ApproverProfileRepository profileRepository;

    @Autowired
    private EventBus eventBus;


    @Override
    public Optional<RequestApproval> register(RequestApproval requestApproval) {

        if (requestApproval.getProposal() == null || requestApproval.getProposal().isNew()) {
            hasErrors(Sets.newHashSet("request.approval.without.proposal")).throwing(ValidationException.class);
        }

        Optional<RequestApproval> requestApprovalLoaded = repository.findByProposal(requestApproval.getProposal());

        if (requestApprovalLoaded.isPresent()) {
            return Optional.of(requestApprovalLoaded.get());
        }

        //TODO exluir o usuario alterado
        if (!profileRepository.hasApprovers()) return Optional.empty();

        Page<ApproverProfile> profilesAvailables = profileRepository.findAll(Pager.build().withPageSize(10000));
        long countProfilesAvailables = profilesAvailables.getContent().stream().filter(item -> item.getAvailable()).count();

        if (countProfilesAvailables == 0) return Optional.empty();

        profilesAvailables.getContent().stream()
                .filter(item -> item.getAvailable() && !item.getApprover().equals(requestApproval.getUserRequester()))
                .forEach(availableItem -> requestApproval.addApprover(createApprover().withApprover(availableItem.getApprover())
                    .withProposal(requestApproval)
                    .withStatus(ApproverStatus.WAITING).build())
        );

        if (requestApproval.getApprovers().isEmpty()) return Optional.empty();

        eventBus.post(RequestNewApprovalMessage.create(requestApproval));
        return Optional.ofNullable(super.save(requestApproval));
    }

    @Override
    public void evaluationApprover(BusinessProposal proposal, User user, ApproverStatus status) {
        
        Optional<RequestApproval> requestApprovalLoaded = repository.findByProposal(proposal);

        if (!requestApprovalLoaded.isPresent()) return;

        Optional<Approver> approverFound = requestApprovalLoaded.get().getApprovers().stream()
                .filter(item -> item.getApprover().equals(user) && item.getStatus().equals(ApproverStatus.WAITING))
                .findFirst();

        if (approverFound.isPresent()) {
            approverFound.get().setStatus(status);
            RequestApproval requestApproval = requestApprovalLoaded.get();
            requestApproval.setCurrentStatus();

            if (!requestApproval.getStatus().equals(RequestApproval.RequestApprovalStatus.WAITING)) {
                eventBus.post(RequestApprovalFinalizeMessage.create(requestApproval));
            }
        }

    }

    @Override
    public Optional<RequestApproval> hasRequestApprovalWaitingfor(BusinessProposal proposal) {
        if (proposal == null || proposal.isNew()) return Optional.empty();
        return  repository.findByProposal(proposal);
    }

    @Override
    public Optional<RequestApproval> findLastRequestApproval(BusinessProposal proposal) {
        if (proposal == null || proposal.isNew()) return Optional.empty();

        Page<RequestApproval> result = this.repository.findLastRequestApprovals(proposal, Pager.build().withPageSize(1));

        if (result.getContent().isEmpty()) return Optional.empty();

        return Optional.of(result.getContent().get(0));
    }


    public BaseRepository<RequestApproval, Long> getRepository() {
        return repository;
    }
}
