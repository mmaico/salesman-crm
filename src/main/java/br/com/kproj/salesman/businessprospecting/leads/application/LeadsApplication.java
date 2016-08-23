package br.com.kproj.salesman.businessprospecting.leads.application;


import br.com.kproj.salesman.infrastructure.entity.leads.Lead;
import br.com.kproj.salesman.infrastructure.service.ModelLegacyService;

public interface LeadsApplication extends ModelLegacyService<Lead> {

    Lead save(Lead lead);
}
