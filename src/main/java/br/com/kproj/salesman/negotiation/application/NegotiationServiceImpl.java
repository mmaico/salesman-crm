package br.com.kproj.salesman.negotiation.application;

import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.helpers.BeanUtils;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.repository.BusinessProposalRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import br.com.kproj.salesman.negotiation.domain.proposal.BusinessProposalDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static br.com.kproj.salesman.infrastructure.helpers.ModelHelper.isNull;
import static com.google.common.collect.Sets.newHashSet;
import static java.util.Collections.emptySet;

@Service
public class NegotiationServiceImpl extends BaseModelServiceImpl<BusinessProposal> implements NegotiationService {

	@Autowired
	private BusinessProposalDomainService service;
	
    @Autowired
    private BusinessProposalRepository repository;

    public BusinessProposal register(BusinessProposal businessProposal) {

        if (!businessProposal.isNew()) {
            BusinessProposal loadBusinessProposal = repository.findOne(businessProposal.getId());
            hasErrors(isNull(loadBusinessProposal) ? newHashSet("proposal.notfound") : emptySet())
                    .throwing(ValidationException.class);

            BeanUtils.create().copyProperties(loadBusinessProposal, businessProposal);
            service.checkBusinessRulesFor(loadBusinessProposal);
            return repository.save(loadBusinessProposal);
        } else {
            service.checkBusinessRulesFor(businessProposal);
            return repository.save(businessProposal);
        }

    }

    @Override
    public BaseRepository<BusinessProposal, Long> getRepository() {
        return repository;
    }
}
