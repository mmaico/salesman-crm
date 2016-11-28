package br.com.kproj.salesman.negotiation.saleable_negotiated.infrastructure.persistence;

import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleable.Saleable;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleable.SaleableRepository;
import br.com.kproj.salesman.negotiation.saleable_negotiated.infrastructure.persistence.springdata.SaleableUnitRepositorySpringData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SaleableRepositoryHibernate extends BaseRespositoryImpl<Saleable, SaleableUnitEntity> implements SaleableRepository {

    @Autowired
    private SaleableUnitRepositorySpringData repository;


    @Override
    public BaseRepositoryLegacy<SaleableUnitEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<SaleableUnitEntity, Saleable> getConverter() {
        return null;
    }
}
