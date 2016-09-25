package br.com.kproj.salesman.business_prospecting.leads.application;


import br.com.kproj.salesman.business_prospecting.leads.domain.model.lead.Lead;
import br.com.kproj.salesman.business_prospecting.leads.domain.model.lead.LeadChange;
import br.com.kproj.salesman.infrastructure.service.ModelFacade;

import java.util.Optional;

public interface LeadFacade extends ModelFacade<Lead> {

    Optional<Lead> register(LeadChange change);

}
