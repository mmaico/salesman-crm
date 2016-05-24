package br.com.kproj.salesman.assistants.campaigns.application;

import br.com.kproj.salesman.assistants.campaigns.domain.CampaignsDomainService;
import br.com.kproj.salesman.infrastructure.entity.Incident;
import br.com.kproj.salesman.infrastructure.entity.campaigns.Campaign;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.repository.CampaignRepository;
import br.com.kproj.salesman.infrastructure.repository.IncidentRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CampaignsApplicationImpl extends BaseModelServiceImpl<Campaign> implements CampaignsApplication {

    @Autowired
    private CampaignRepository repository;

    @Autowired
    private CampaignsDomainService service;

    @Override
    public BaseRepository<Campaign, Long> getRepository() {
        return repository;
    }


    @Override
    public Campaign save(Campaign campaign) {

        return super.save(campaign, service);
    }
}
