package br.com.kproj.salesman.business_prospecting.leads.application;


import br.com.kproj.salesman.business_prospecting.leads.domain.model.lead.Lead;
import br.com.kproj.salesman.business_prospecting.leads.domain.model.lead.LeadChange;
import br.com.kproj.salesman.business_prospecting.leads.domain.model.lead.LeadRepository;
import br.com.kproj.salesman.business_prospecting.leads.domain.model.lead.LeadValidator;
import br.com.kproj.salesman.business_prospecting.leads.domain.model.user.User;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LeadServiceImpl extends BaseModelServiceImpl<Lead> implements LeadFacade {

    private LeadRepository repository;
    private LeadValidator validator;

    @Autowired
    public LeadServiceImpl(LeadRepository repository, LeadValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    @Override
    public Optional<Lead> register(LeadChange change) {
        User user = change.getUser();
        Lead lead = change.getLead();

        return user.save(lead);
    }

    @Override
    public BaseRepository<Lead, Long> getRepository() {
        return repository;
    }
}
