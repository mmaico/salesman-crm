package br.com.kproj.salesman.incidents.application;


import br.com.kproj.salesman.infrastructure.entity.Incident;
import br.com.kproj.salesman.infrastructure.service.ModelLegacyService;

public interface IncidentApplication extends ModelLegacyService<Incident> {

    Incident save(Incident incident);
}
