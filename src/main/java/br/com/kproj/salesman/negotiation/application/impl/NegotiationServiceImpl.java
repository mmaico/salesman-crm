package br.com.kproj.salesman.negotiation.application.impl;

import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.repository.BusinessProposalRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kproj.salesman.negotiation.application.NegotiationService;

@Service
public class NegotiationServiceImpl extends BaseModelServiceImpl<BusinessProposal> implements NegotiationService {

    @Autowired
    private BusinessProposalRepository repository;

    public BusinessProposal register(BusinessProposal businessProposal) {
        return super.save(businessProposal);
    }

    @Override
    public BaseRepository<BusinessProposal, Long> getRepository() {
        return repository;
    }
}
