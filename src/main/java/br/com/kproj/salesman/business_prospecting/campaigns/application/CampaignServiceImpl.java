package br.com.kproj.salesman.business_prospecting.campaigns.application;


import br.com.kproj.salesman.business_prospecting.campaigns.domain.model.campaigns.Campaign;
import br.com.kproj.salesman.business_prospecting.campaigns.domain.model.campaigns.CampaignChange;
import br.com.kproj.salesman.business_prospecting.campaigns.domain.model.campaigns.CampaignRepository;
import br.com.kproj.salesman.business_prospecting.campaigns.domain.model.user.User;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CampaignServiceImpl extends BaseModelServiceImpl<Campaign> implements CampaignsFacade {


    private CampaignRepository repository;

    @Autowired
    public CampaignServiceImpl(CampaignRepository repository) {
        this.repository = repository;
    }

    public Optional<Campaign> register(CampaignChange change) {

        Campaign campaign = change.getCampaign();
        User user = change.getUser();

        return user.save(campaign);
    }


    @Override
    public BaseRepository<Campaign, Long> getRepository() {
        return repository;
    }
}
