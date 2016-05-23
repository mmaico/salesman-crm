package br.com.kproj.salesman.incidents.application;


import br.com.kproj.salesman.infrastructure.entity.Incident;
import br.com.kproj.salesman.infrastructure.service.ModelService;

public interface IncidentApplication extends ModelService<Incident> {

    Incident save(Incident incident);
}
