package br.com.kproj.salesman.business_prospecting.campaigns.domain.model.user;

import br.com.kproj.salesman.business_prospecting.campaigns.domain.model.campaigns.Campaign;
import br.com.kproj.salesman.business_prospecting.campaigns.domain.model.campaigns.CampaignRepository;
import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.trex.shared.annotations.Model;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static br.com.kproj.salesman.infrastructure.helpers.AutowireHelper.autowire;

@Model
public class User extends ModelIdentifiable {

    private Long id;

    @Autowired
    private CampaignRepository repository;

    public User() {
        autowire(this);
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Optional<Campaign> save(Campaign campaign) {
        return repository.save(campaign);
    }
}
