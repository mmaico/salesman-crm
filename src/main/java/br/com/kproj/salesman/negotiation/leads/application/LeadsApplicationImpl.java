package br.com.kproj.salesman.negotiation.leads.application;

import br.com.kproj.salesman.infrastructure.entity.leads.Lead;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import br.com.kproj.salesman.negotiation.infrastructure.repository.LeadRepository;
import br.com.kproj.salesman.negotiation.leads.domain.LeadsDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeadsApplicationImpl extends BaseModelServiceImpl<Lead> implements LeadsApplication {

    @Autowired
    private LeadRepository repository;

    @Autowired
    private LeadsDomainService service;

    @Override
    public BaseRepository<Lead, Long> getRepository() {
        return repository;
    }


    @Override
    public Lead save(Lead lead) {

        return super.save(lead, service);
    }
}
