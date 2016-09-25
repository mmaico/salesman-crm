package br.com.kproj.salesman.incidents.domain.model.incident;


public interface IncidentValidator {

    void checkRules(IncidentChange change);
}
