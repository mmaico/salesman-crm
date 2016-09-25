package br.com.kproj.salesman.business_prospecting.leads.view;


import br.com.kproj.salesman.business_prospecting.leads.application.LeadFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LeadsEndpoint {

    @Autowired
    private LeadFacade service;


}
