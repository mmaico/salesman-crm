package br.com.kproj.salesman.assistants.calendar.activities.specialization.infrastructure.persistence;


import br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.saleable.Saleable;
import br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.saleable.SaleableRepository;
import br.com.kproj.salesman.assistants.calendar.activities.specialization.infrastructure.persistence.springdata.SaleableEntityRepositorySpringData;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("saleableRepositoryHibernateCalendarModule")
public class SaleableRepositoryHibernate extends BaseRespositoryImpl<Saleable, SaleableUnitEntity> implements SaleableRepository {


    private SaleableEntityRepositorySpringData repository;

    @Autowired
    public SaleableRepositoryHibernate(SaleableEntityRepositorySpringData repository) {
        this.repository = repository;
    }

    @Override
    public BaseRepositoryLegacy<SaleableUnitEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<SaleableUnitEntity, Saleable> getConverter() {
        return ((saleableUnitEntity, args) -> new Saleable(saleableUnitEntity.getId()));
    }
}
