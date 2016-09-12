package br.com.kproj.salesman.delivery2.tasks_template.infrastructure.persistence;

import br.com.kproj.salesman.delivery2.tasks_template.infrastructure.persistence.springdata.SaleableRepositorySpringData;
import br.com.kproj.salesman.delivery2.tasks_template.model.product.Saleable;
import br.com.kproj.salesman.delivery2.tasks_template.model.product.SaleableRepository;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("saleableTaskTemplateRepository")
public class SaleableRepositoryHibernate extends BaseRespositoryImpl<Saleable, SaleableUnitEntity> implements SaleableRepository {

    @Autowired
    private SaleableRepositorySpringData repository;

    @Override
    public BaseRepositoryLegacy<SaleableUnitEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<SaleableUnitEntity, Saleable> getConverter() {
        return (saleableUnitEntity, args) -> {
            Saleable saleable = new Saleable();
            saleable.setId(saleableUnitEntity.getId());
            return saleable;
        };
    }
}
