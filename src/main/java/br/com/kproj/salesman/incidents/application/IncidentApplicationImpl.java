package br.com.kproj.salesman.incidents.application;

import br.com.kproj.salesman.incidents.domain.IncidentDomainService;
import br.com.kproj.salesman.infrastructure.entity.Incident;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.IncidentRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceLegacyImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncidentApplicationImpl extends BaseModelServiceLegacyImpl<Incident> implements IncidentApplication {

    @Autowired
    private IncidentRepository repository;

    @Autowired
    private IncidentDomainService service;

    @Override
    public BaseRepositoryLegacy<Incident, Long> getRepository() {
        return repository;
    }


    @Override
    public Incident save(Incident incident) {

        return super.save(incident, service);
    }
}
