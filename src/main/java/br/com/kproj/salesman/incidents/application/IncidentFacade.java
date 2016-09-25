package br.com.kproj.salesman.incidents.application;


import br.com.kproj.salesman.incidents.domain.model.incident.Incident;
import br.com.kproj.salesman.incidents.domain.model.incident.IncidentChange;
import br.com.kproj.salesman.infrastructure.service.ModelFacade;

import java.util.Optional;

public interface IncidentFacade extends ModelFacade<Incident> {

    Optional<Incident> register(IncidentChange change);
}
