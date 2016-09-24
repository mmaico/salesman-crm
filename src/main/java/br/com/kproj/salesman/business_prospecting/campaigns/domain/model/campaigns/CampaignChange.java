package br.com.kproj.salesman.business_prospecting.campaigns.domain.model.campaigns;

import br.com.kproj.salesman.business_prospecting.campaigns.domain.model.user.User;
import br.com.kproj.salesman.infrastructure.model.ValueObject;


public class CampaignChange implements ValueObject {

    private final Campaign campaign;
    private final User user;

    public CampaignChange(Campaign campaign, User user) {
        this.campaign = campaign;
        this.user = user;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public User getUser() {
        return user;
    }

    public static CampaignChange newCampaignChange(Campaign campaign, User user) {
        return new CampaignChange(campaign, user);
    }
}
