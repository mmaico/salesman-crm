package br.com.kproj.salesman.negotiation.application;

import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.repository.BusinessProposalRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import br.com.kproj.salesman.negotiation.domain.proposal.BusinessProposalDomainService;
import br.com.kproj.salesman.register.application.prepare.PreUpdateItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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

    public BusinessProposal register(BusinessProposal businessProposal) {

        service.checkBusinessRulesFor(businessProposal);
        productsPrepare.preUpdate(businessProposal);
        paymentPrepare.preUpdate(businessProposal);

        if (!businessProposal.isNew()) {
            return super.save(businessProposal, service);
        } else {
            return super.save(businessProposal);
        }
    }

    @Override
    public BaseRepository<BusinessProposal, Long> getRepository() {
        return repository;
    }
}
