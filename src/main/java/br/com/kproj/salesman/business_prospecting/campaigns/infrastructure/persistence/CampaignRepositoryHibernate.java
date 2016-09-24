package br.com.kproj.salesman.business_prospecting.campaigns.infrastructure.persistence;


import br.com.kproj.salesman.business_prospecting.campaigns.domain.model.campaigns.Campaign;
import br.com.kproj.salesman.business_prospecting.campaigns.domain.model.campaigns.CampaignRepository;
import br.com.kproj.salesman.business_prospecting.campaigns.infrastructure.persistence.springdata.CampaignRepositorySpringData;
import br.com.kproj.salesman.infrastructure.entity.campaigns.CampaignEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CampaignRepositoryHibernate extends BaseRespositoryImpl<Campaign, CampaignEntity> implements CampaignRepository {

    private CampaignRepositorySpringData repository;

    @Autowired
    public CampaignRepositoryHibernate(CampaignRepositorySpringData repository) {
        this.repository = repository;
    }

    @Override
    public BaseRepositoryLegacy<CampaignEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<CampaignEntity, Campaign> getConverter() {
        return null;
    }
}
