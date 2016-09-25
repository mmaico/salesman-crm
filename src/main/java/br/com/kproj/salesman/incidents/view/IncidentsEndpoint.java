package br.com.kproj.salesman.incidents.view;


import br.com.kproj.salesman.incidents.application.IncidentFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IncidentsEndpoint {

    @Autowired
    private IncidentFacade service;




}
