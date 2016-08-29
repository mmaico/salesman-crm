package br.com.kproj.salesman.negotiation2.infrastructure.persistence;

import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.negotiation2.domain.model.product.Saleable;
import br.com.kproj.salesman.negotiation2.domain.model.product.SaleableRepository;
import br.com.kproj.salesman.negotiation2.infrastructure.persistence.springdata.SaleableUnitRepositorySpringData;
import br.com.kproj.salesman.negotiation2.infrastructure.persistence.translate.products.SaleableUnitEntityToSaleableConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class SaleableRepositoryHibernate extends BaseRespositoryImpl<Saleable, SaleableUnitEntity> implements SaleableRepository {

    @Autowired
    @Qualifier("saleableUnitRepositoryNegotiation")
    private SaleableUnitRepositorySpringData repository;

    @Autowired
    private SaleableUnitEntityToSaleableConverter converter;



    @Override
    public BaseRepositoryLegacy<SaleableUnitEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<SaleableUnitEntity, Saleable> getConverter() {
        return converter;
    }
}
