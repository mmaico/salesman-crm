package br.com.kproj.salesman.auditing.application;

import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.auditing.BusinessProposalAudinting;
import br.com.kproj.salesman.infrastructure.entity.builders.BusinessProposalAuditingBuilder;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.events.messages.ProposalAuditingAfterUpdateMessage;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BusinessProposalAuditingRepository;
import br.com.kproj.salesman.infrastructure.repository.Pager;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceLegacyImpl;
import com.google.common.eventbus.EventBus;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BusinessProposalAuditingApplicationImpl extends BaseModelServiceLegacyImpl<BusinessProposalAudinting> implements BusinessProposalAuditingApplication {

    @Autowired
    private BusinessProposalAuditingRepository repository;

    @Autowired
    private Gson gson;

    @Autowired
    private EventBus eventBus;

    public Optional<BusinessProposalAudinting> registerAuditing(BusinessProposal businessProposal, User userThatChanged) {

        Page<BusinessProposalAudinting> lasModitication = repository.findLasVersion(businessProposal.getId(), Pager.build().withPageSize(1));

        BusinessProposalAudinting newEntryAuditable = BusinessProposalAuditingBuilder.createAuditing()
                .withEntityId(businessProposal.getId())
                .setCurrentDate()
                .withInfo(gson.toJson(businessProposal))
                .withUser(userThatChanged).build();

        if (lasModitication.getContent().size() == 0) {
            return Optional.ofNullable(repository.save(newEntryAuditable));
        } else {
            BusinessProposalAudinting before = lasModitication.getContent().get(0);
            if (!before.getInfo().equals(newEntryAuditable.getInfo())) {
                BusinessProposalAudinting after = repository.save(newEntryAuditable);

                eventBus.post(ProposalAuditingAfterUpdateMessage.create(before, after, userThatChanged));

                return Optional.ofNullable(after);
            }
        }

        return Optional.empty();
    }

    @Override
    public Page<BusinessProposalAudinting> findLogs(Long businessEntityId, Pager pager) {
        return repository.findAll(businessEntityId, pager);
    }

    public BaseRepositoryLegacy<BusinessProposalAudinting, Long> getRepository() {
        return this.repository;
    }
}
