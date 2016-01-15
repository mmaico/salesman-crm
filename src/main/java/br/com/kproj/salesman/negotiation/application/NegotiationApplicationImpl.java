package br.com.kproj.salesman.negotiation.application;

import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.enums.ProposalTemperature;
import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.events.messages.BusinessProposalClosedWonMessage;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.repository.BusinessProposalRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import br.com.kproj.salesman.negotiation.domain.proposal.BusinessProposalDomainService;
import br.com.kproj.salesman.negotiation.domain.proposal.CanChangeProposalStatusDomainService;
import br.com.kproj.salesman.register.application.prepare.PreUpdateItems;
import com.google.common.collect.Lists;
import com.google.common.eventbus.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NegotiationApplicationImpl extends BaseModelServiceImpl<BusinessProposal> implements NegotiationApplication {

	@Autowired
	private BusinessProposalDomainService service;

    @Autowired
    @Qualifier("paymentPreUpdate")
    private PreUpdateItems paymentPrepare;

    @Autowired
    @Qualifier("productsPreUpdate")
    private PreUpdateItems productsPrepare;
	
    @Autowired
    private BusinessProposalRepository repository;

    @Autowired
    private CanChangeProposalStatusDomainService checkChange;

    @Autowired
    private EventBus eventBus;

    public BusinessProposal register(BusinessProposal businessProposal) {

        service.checkBusinessRulesFor(businessProposal);
        productsPrepare.preUpdate(businessProposal);
        paymentPrepare.preUpdate(businessProposal);
        BusinessProposal businessProposalSaved = null;

        if (!businessProposal.isNew()) {
            businessProposalSaved = super.save(businessProposal, service);
        } else {
            businessProposal.setTemperature(ProposalTemperature.COLD);
            businessProposalSaved =  super.save(businessProposal);
        }

        eventBus.post(businessProposal);
        return businessProposalSaved;
    }

    @Override
    public List<BusinessProposal> findByClient(Person client) {
        if (client == null || client.isNew()) {
            return Lists.newArrayList();
        }
        return repository.findByClient(client);
    }

    @Override
    public void changeTemperature(BusinessProposal proposal, User changeThat, ProposalTemperature temperature) {

        if (temperature == null) return;

        Optional<BusinessProposal> proposalOptional = getOne(proposal.getId());

        if (proposalOptional.isPresent()) {
            BusinessProposal businessProposal = proposalOptional.get();
            if (ProposalTemperature.CLOSED_WON == temperature) {
                if (checkChange.isValidBusinessRulesFor(proposal, changeThat)) {
                    businessProposal.setTemperature(temperature);
                    eventBus.post(BusinessProposalClosedWonMessage.create(businessProposal));
                }
            } else {
                businessProposal.setTemperature(temperature);
            }
        }
    }

    @Override
    public BaseRepository<BusinessProposal, Long> getRepository() {
        return repository;
    }
}
