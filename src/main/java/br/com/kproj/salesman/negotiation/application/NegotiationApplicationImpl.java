package br.com.kproj.salesman.negotiation.application;

import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.repository.BusinessProposalRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import br.com.kproj.salesman.negotiation.domain.proposal.BusinessProposalDomainService;
import br.com.kproj.salesman.register.application.prepare.PreUpdateItems;
import com.google.common.collect.Lists;
import com.google.common.eventbus.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

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
    private EventBus eventBus;

    public BusinessProposal register(BusinessProposal businessProposal) {

        service.checkBusinessRulesFor(businessProposal);
        productsPrepare.preUpdate(businessProposal);
        paymentPrepare.preUpdate(businessProposal);
        BusinessProposal businessProposalSaved = null;

        if (!businessProposal.isNew()) {
            businessProposalSaved = super.save(businessProposal, service);
        } else {
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
    public BaseRepository<BusinessProposal, Long> getRepository() {
        return repository;
    }
}
