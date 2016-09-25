package br.com.kproj.salesman.business_prospecting.leads.domain.model.lead;


public interface LeadValidator {

    void checkRules(LeadChange change);
}
