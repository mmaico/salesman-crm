package br.com.kproj.salesman.auditing.application;

import br.com.kproj.salesman.infrastructure.entity.auditing.BusinessProposalAudinting;
import br.com.kproj.salesman.infrastructure.entity.builders.BusinessProposalAuditingBuilder;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.repository.BusinessProposalAuditingRepository;
import br.com.kproj.salesman.infrastructure.repository.Pager;
import br.com.kproj.salesman.infrastructure.security.authentication.LoggedUser;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import br.com.kproj.salesman.negotiation.application.NegotiationApplication;

import java.util.Optional;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class BusinessProposalAuditingApplicationImpl extends BaseModelServiceImpl<BusinessProposalAudinting> implements BusinessProposalAuditingApplication {

    @Autowired
    private BusinessProposalAuditingRepository repository;

    @Autowired
    private Gson gson;

    public Optional<BusinessProposalAudinting> registerAuditing(BusinessProposal businessProposal) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoggedUser principal = (LoggedUser)authentication.getDetails();

        Page<BusinessProposalAudinting> lasModitication = repository.findLasVersion(Pager.build().withPageSize(1));

        BusinessProposalAudinting newEntryAuditable = BusinessProposalAuditingBuilder.createAuditing()
                .withEntityId(businessProposal.getId())
                .setCurrentDate()
                .withInfo(gson.toJson(businessProposal))
                .withUser(principal.getUser()).build();

        if (lasModitication.getContent().size() == 0) {
            Optional.ofNullable(repository.save(newEntryAuditable));
        } else {
            if (!lasModitication.getContent().get(0).getInfo().equals(newEntryAuditable.getInfo())) {
                Optional.ofNullable(repository.save(newEntryAuditable));
            }
        }

        return Optional.empty();
    }

    @Override
    public BaseRepository<BusinessProposalAudinting, Long> getRepository() {
        return this.repository;
    }
}
