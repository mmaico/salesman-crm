package br.com.kproj.salesman.business_prospecting.leads.infrastructure.persistence;


import br.com.kproj.salesman.business_prospecting.leads.domain.model.lead.Lead;
import br.com.kproj.salesman.business_prospecting.leads.domain.model.lead.LeadRepository;
import br.com.kproj.salesman.business_prospecting.leads.infrastructure.persistence.springdata.LeadRepositorySpringData;
import br.com.kproj.salesman.infrastructure.entity.leads.LeadEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LeadRepositoryHibernate extends BaseRespositoryImpl<Lead, LeadEntity> implements LeadRepository {

    private LeadRepositorySpringData repository;

    @Autowired
    public LeadRepositoryHibernate(LeadRepositorySpringData repository) {
        this.repository = repository;
    }

    @Override
    public BaseRepositoryLegacy<LeadEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<LeadEntity, Lead> getConverter() {
        return null;
    }
}
