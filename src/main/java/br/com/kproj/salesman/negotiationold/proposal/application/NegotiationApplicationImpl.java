package br.com.kproj.salesman.negotiationold.proposal.application;

import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.builders.SalesOrderBuilder;
import br.com.kproj.salesman.infrastructure.entity.enums.ProposalTemperature;
import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrder;
import br.com.kproj.salesman.infrastructure.events.messages.BusinessProposalClosedWonMessage;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BusinessProposalRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceLegacyImpl;
import br.com.kproj.salesman.negotiationold.proposal.domain.BusinessProposalDomainService;
import br.com.kproj.salesman.negotiationold.proposal.domain.CanChangeProposalStatusDomainService;
import br.com.kproj.salesman.register.application.prepare.PreUpdateItems;
import com.google.common.collect.Lists;
import com.google.common.eventbus.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NegotiationApplicationImpl extends BaseModelServiceLegacyImpl<BusinessProposalEntity> implements NegotiationApplication {

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

    public BusinessProposalEntity register(BusinessProposalEntity businessProposalEntity) {

        service.checkBusinessRulesFor(businessProposalEntity);
        productsPrepare.preUpdate(businessProposalEntity);
        paymentPrepare.preUpdate(businessProposalEntity);
        BusinessProposalEntity businessProposalEntitySaved = null;

        if (!businessProposalEntity.isNew()) {
            businessProposalEntitySaved = super.save(businessProposalEntity, service);
        } else {
            businessProposalEntity.setTemperature(ProposalTemperature.COLD);
            businessProposalEntitySaved =  super.save(businessProposalEntity);
        }

        eventBus.post(businessProposalEntity);
        return businessProposalEntitySaved;
    }

    @Override
    public List<BusinessProposalEntity> findByClient(Person client) {
        if (client == null || client.isNew()) {
            return Lists.newArrayList();
        }
        return null; //repository.findByClient(client);
    }

    @Override
    public SalesOrder findSalesBy(BusinessProposalEntity businessProposalEntity) {

        if (businessProposalEntity == null || businessProposalEntity.isNew()) {
            return SalesOrderBuilder.createSalesOrder().build();
        }

        return repository.findByProposal(businessProposalEntity);
    }

    @Override
    public void changeTemperature(BusinessProposalEntity proposal, UserEntity changeThat, ProposalTemperature temperature) {

        if (temperature == null) return;

        Optional<BusinessProposalEntity> proposalOptional = getOne(proposal.getId());

        if (proposalOptional.isPresent()) {

            BusinessProposalEntity businessProposalEntity = proposalOptional.get();
            if (businessProposalEntity.getTemperature() == ProposalTemperature.CLOSED_WON) return;

            if (ProposalTemperature.CLOSED_WON == temperature) {
                if (checkChange.isValidBusinessRulesFor(proposal, changeThat)) {
                    businessProposalEntity.setTemperature(temperature);
                    eventBus.post(BusinessProposalClosedWonMessage.create(businessProposalEntity));
                }
            } else {
                businessProposalEntity.setTemperature(temperature);
            }
        }
    }

    public BaseRepositoryLegacy<BusinessProposalEntity, Long> getRepository() {
        return repository;
    }
}
