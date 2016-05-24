package br.com.kproj.salesman.assistants.campaigns.application;


import br.com.kproj.salesman.infrastructure.entity.campaigns.Campaign;
import br.com.kproj.salesman.infrastructure.service.ModelService;

public interface CampaignsApplication extends ModelService<Campaign> {

    Campaign save(Campaign campaign);
}
