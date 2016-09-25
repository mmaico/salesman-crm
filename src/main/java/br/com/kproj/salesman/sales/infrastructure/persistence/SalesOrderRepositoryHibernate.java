package br.com.kproj.salesman.sales.infrastructure.persistence;


import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrderEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.sales.domain.model.sales.SalesOrder;
import br.com.kproj.salesman.sales.domain.model.sales.SalesOrderRepository;
import br.com.kproj.salesman.sales.infrastructure.generatesale.convert.NegotiationToSaleOrder;
import br.com.kproj.salesman.sales.infrastructure.persistence.springdata.SalesOrderRepositorySpringdata;
import br.com.kproj.salesman.sales.view.dtos.negotiation.NegotiationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import static com.trex.clone.BusinessModelClone.from;

@Repository
public class SalesOrderRepositoryHibernate extends BaseRespositoryImpl<SalesOrder, SalesOrderEntity> implements SalesOrderRepository {

    private SalesOrderRepositorySpringdata repository;

    private NegotiationToSaleOrder converter;

    @Autowired
    public SalesOrderRepositoryHibernate(SalesOrderRepositorySpringdata repository, NegotiationToSaleOrder converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public SalesOrder generateBy(NegotiationDTO negotiationDTO) {
        SalesOrderEntity orderEntity = converter.convert(negotiationDTO);
        return getConverter().convert(repository.save(orderEntity));
    }

    @Override
    public BaseRepositoryLegacy<SalesOrderEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<SalesOrderEntity, SalesOrder> getConverter() {
        return (salesOrderEntity, args) -> from(salesOrderEntity).convertTo(SalesOrder.class);
    }


}
