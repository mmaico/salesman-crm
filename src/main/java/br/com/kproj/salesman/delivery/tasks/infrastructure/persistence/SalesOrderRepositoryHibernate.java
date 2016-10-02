package br.com.kproj.salesman.delivery.tasks.infrastructure.persistence;

import br.com.kproj.salesman.delivery.tasks.domain.model.sales.SalesOrder;
import br.com.kproj.salesman.delivery.tasks.domain.model.sales.SalesOrderRepository;
import br.com.kproj.salesman.delivery.tasks.infrastructure.persistence.springdata.SalesOrderRepositorySpringdata;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrderEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository("salesOrderRepositoryHibernateTaskModule")
public class SalesOrderRepositoryHibernate extends BaseRespositoryImpl<SalesOrder, SalesOrderEntity> implements SalesOrderRepository {

    @Autowired
    private SalesOrderRepositorySpringdata repository;


    @Override
    public BaseRepositoryLegacy<SalesOrderEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<SalesOrderEntity, SalesOrder> getConverter() {
        return ((salesOrderEntity, args) -> new SalesOrder(salesOrderEntity.getId()));
    }
}
