package br.com.kproj.salesman.assistants.campaigns.application;


import br.com.kproj.salesman.infrastructure.entity.campaigns.Campaign;
import br.com.kproj.salesman.infrastructure.service.ModelLegacyService;

public interface CampaignsApplication extends ModelLegacyService<Campaign> {

    Campaign save(Campaign campaign);
}
