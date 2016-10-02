package br.com.kproj.salesman.delivery.tasks_template.infrastructure.persistence;

import br.com.kproj.salesman.delivery.tasks_template.infrastructure.persistence.springdata.RegionRepositorySpringData;
import br.com.kproj.salesman.delivery.tasks_template.model.region.Region;
import br.com.kproj.salesman.delivery.tasks_template.model.region.RegionRepository;
import br.com.kproj.salesman.infrastructure.entity.OperationRegionEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("regionTemplateRepositoryTaskTemplateModule")
public class RegionRepositoryHibernate extends BaseRespositoryImpl<Region, OperationRegionEntity> implements RegionRepository {

    private RegionRepositorySpringData repository;

    @Autowired
    public RegionRepositoryHibernate(RegionRepositorySpringData repository) {
        this.repository = repository;
    }

    @Override
    public BaseRepositoryLegacy<OperationRegionEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<OperationRegionEntity, Region> getConverter() {
        return (operationRegionEntity, args) -> {
            Region region = new Region();
            region.setId(operationRegionEntity.getId());
            return region;
        };
    }
}
