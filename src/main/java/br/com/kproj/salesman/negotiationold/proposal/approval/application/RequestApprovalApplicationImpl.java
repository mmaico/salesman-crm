package br.com.kproj.salesman.negotiationold.proposal.approval.application;

import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.enums.ApproverStatus;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;
import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.Approver;
import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.ApproverProfile;
import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.RequestApprovalEntity;
import br.com.kproj.salesman.infrastructure.events.messages.RequestApprovalFinalizeMessage;
import br.com.kproj.salesman.infrastructure.events.messages.RequestNewApprovalMessage;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.repository.ApproverProfileRepository;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.Pager;
import br.com.kproj.salesman.infrastructure.repository.RequestApprovalRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceLegacyImpl;
import com.google.common.collect.Sets;
import com.google.common.eventbus.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static br.com.kproj.salesman.infrastructure.entity.builders.ApproverBuilder.createApprover;
import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;

@Service
public class RequestApprovalApplicationImpl extends BaseModelServiceLegacyImpl<RequestApprovalEntity> implements RequestApprovalApplication {

    @Autowired
    private RequestApprovalRepository repository;

    @Autowired
    private ApproverProfileRepository profileRepository;

    @Autowired
    private EventBus eventBus;


    @Override
    public Optional<RequestApprovalEntity> register(RequestApprovalEntity requestApprovalEntity) {

        if (requestApprovalEntity.getProposal() == null || requestApprovalEntity.getProposal().isNew()) {
            hasErrors(Sets.newHashSet("request.approval.without.domain")).throwing(ValidationException.class);
        }

        Optional<RequestApprovalEntity> requestApprovalLoaded = repository.findByProposal(requestApprovalEntity.getProposal());

        if (requestApprovalLoaded.isPresent()) {
            return Optional.of(requestApprovalLoaded.get());
        }

        //TODO exluir o usuarios desativados
        if (!profileRepository.hasApprovers()) return Optional.empty();

        Page<ApproverProfile> profilesAvailables = profileRepository.findAll(Pager.build().withPageSize(10000));
        long countProfilesAvailables = profilesAvailables.getContent().stream().filter(item -> item.getAvailable()).count();

        if (countProfilesAvailables == 0) return Optional.empty();

        profilesAvailables.getContent().stream()
                .filter(item -> item.getAvailable() && !item.getApprover().equals(requestApprovalEntity.getUserRequester()))
                .forEach(availableItem -> requestApprovalEntity.addApprover(createApprover().withApprover(availableItem.getApprover())
                    .withProposal(requestApprovalEntity)
                    .withStatus(ApproverStatus.WAITING).build())
        );

        if (requestApprovalEntity.getApprovers().isEmpty()) return Optional.empty();

        eventBus.post(RequestNewApprovalMessage.create(requestApprovalEntity));
        return Optional.ofNullable(super.save(requestApprovalEntity));
    }

    @Override
    public void evaluationApprover(BusinessProposalEntity proposal, UserEntity user, ApproverStatus status) {
        
        Optional<RequestApprovalEntity> requestApprovalLoaded = repository.findByProposal(proposal);

        if (!requestApprovalLoaded.isPresent()) return;

        Optional<Approver> approverFound = requestApprovalLoaded.get().getApprovers().stream()
                .filter(item -> item.getApprover().equals(user) && item.getStatus().equals(ApproverStatus.WAITING))
                .findFirst();

        if (approverFound.isPresent()) {
            approverFound.get().setStatus(status);
            RequestApprovalEntity requestApprovalEntity = requestApprovalLoaded.get();
            requestApprovalEntity.setCurrentStatus();

            if (!requestApprovalEntity.getStatus().equals(RequestApprovalEntity.RequestApprovalStatus.WAITING)) {
                eventBus.post(RequestApprovalFinalizeMessage.create(requestApprovalEntity));
            }
        }

    }

    @Override
    public Optional<RequestApprovalEntity> hasRequestApprovalWaitingfor(BusinessProposalEntity proposal) {
        if (proposal == null || proposal.isNew()) return Optional.empty();
        return  repository.findByProposal(proposal);
    }

    @Override
    public Optional<RequestApprovalEntity> findLastRequestApproval(BusinessProposalEntity proposal) {
        if (proposal == null || proposal.isNew()) return Optional.empty();

        Page<RequestApprovalEntity> result = this.repository.findLastRequestApprovals(proposal, Pager.build().withPageSize(1));

        if (result.getContent().isEmpty()) return Optional.empty();

        return Optional.of(result.getContent().get(0));
    }


    public BaseRepositoryLegacy<RequestApprovalEntity, Long> getRepository() {
        return repository;
    }
}
