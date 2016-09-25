package br.com.kproj.salesman.incidents.application;


import br.com.kproj.salesman.incidents.domain.model.incident.Incident;
import br.com.kproj.salesman.incidents.domain.model.incident.IncidentChange;
import br.com.kproj.salesman.incidents.domain.model.incident.IncidentRepository;
import br.com.kproj.salesman.incidents.domain.model.incident.IncidentValidator;
import br.com.kproj.salesman.incidents.domain.model.user.User;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IndicentServiceImpl extends BaseModelServiceImpl<Incident> implements IncidentFacade {

    private IncidentRepository repository;

    private IncidentValidator rules;

    @Autowired
    public IndicentServiceImpl(IncidentRepository repository, IncidentValidator rules) {
        this.repository = repository;
        this.rules = rules;
    }


    public Optional<Incident> register(IncidentChange change) {
        rules.checkRules(change);

        User user = change.getUser();
        Incident incident = change.getIncident();

        return user.save(incident);
    }

    @Override
    public BaseRepository<Incident, Long> getRepository() {
        return repository;
    }
}
