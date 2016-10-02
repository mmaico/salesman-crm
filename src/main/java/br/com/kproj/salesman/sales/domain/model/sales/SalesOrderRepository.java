package br.com.kproj.salesman.sales.domain.model.sales;


import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.sales.domain.model.negotiation.INegotiation;

public interface SalesOrderRepository extends BaseRepository<SalesOrder, Long> {

    SalesOrder generateBy(INegotiation negotiation);

}

