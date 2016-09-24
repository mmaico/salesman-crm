package br.com.kproj.salesman.business_prospecting.campaigns.view;


import br.com.kproj.salesman.business_prospecting.campaigns.application.CampaignsFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CampaignsEnpoint {

    @Autowired
    private CampaignsFacade service;




}
