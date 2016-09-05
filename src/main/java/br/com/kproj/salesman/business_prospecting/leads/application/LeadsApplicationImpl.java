package br.com.kproj.salesman.business_prospecting.leads.application;

import br.com.kproj.salesman.business_prospecting.leads.domain.LeadsDomainService;
import br.com.kproj.salesman.infrastructure.entity.leads.Lead;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceLegacyImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeadsApplicationImpl extends BaseModelServiceLegacyImpl<Lead> implements LeadsApplication {

//    @Autowired
//    private LeadRepository repository;

    @Autowired
    private LeadsDomainService service;

    @Override
    public BaseRepositoryLegacy<Lead, Long> getRepository() {
        return null;
    }


    @Override
    public Lead save(Lead lead) {

        return super.save(lead, service);
    }
}
