package br.com.kproj.salesman.sales.domain.model.sales;


import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.sales.view.dtos.negotiation.NegotiationDTO;

public interface SalesOrderRepository extends BaseRepository<SalesOrder, Long> {

    SalesOrder generateBy(NegotiationDTO negotiationDTO);

}

