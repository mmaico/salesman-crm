package br.com.kproj.salesman.negotiation.leads.application;


import br.com.kproj.salesman.infrastructure.entity.leads.Lead;
import br.com.kproj.salesman.infrastructure.service.ModelService;

public interface LeadsApplication extends ModelService<Lead> {

    Lead save(Lead lead);
}
